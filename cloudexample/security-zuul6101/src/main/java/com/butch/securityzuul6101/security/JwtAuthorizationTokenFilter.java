package com.butch.securityzuul6101.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.butch.apiutils.jwt.JwtProperties;
import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.redis.RedisUserUtil;
import com.butch.apiutils.redis.pojo.RedisUserDetails;
import com.butch.securityzuul6101.pojo.UserExDetails;
import com.butch.securityzuul6101.util.SecurityUtil;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * 自定义拦截器，把用户的token添加进上下文中，可以让后面的鉴权之类的拦截器使用到此用户。
 * 
 * 由SecurityUntil建造，目前版本spring过滤器只要自动注入就会自动装配至mvc，不会进入security链过滤静态资源。
 * 真JB的天坑浪费👴的时间
 */
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
	private JwtTokenUtil jwtTokenUtil;
	private JwtProperties jwtProperties;
	private RedisUserUtil redisUserUtil;
	private SecurityUtil securityUtil;

	public JwtAuthorizationTokenFilter(JwtTokenUtil jwtTokenUtil, JwtProperties jwtProperties,
			RedisUserUtil redisUserUtil, SecurityUtil securityUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtProperties = jwtProperties;
		this.redisUserUtil = redisUserUtil;
		this.securityUtil = securityUtil;
	}

	// 获取用户信息并且放入上下文中。鉴权可以直接在上下文中取得权限鉴权。
	@SuppressWarnings("all")
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws ServletException, IOException {
		String requestHeader = null;
		System.out.println("Cookie长度:"+request.getCookies().length+"\n jwtProperties.getToken():"+jwtProperties.getToken());
		for (Cookie cookie : request.getCookies()) {
			if (jwtProperties.getToken().equals(cookie.getName())) {
				if ("null".equals(cookie.getValue()) == false) {
					requestHeader = cookie.getValue();
				}
			}
		}
		System.out.println("-------jwt的值是否为空-----：" + (requestHeader == null));
		System.out.println(requestHeader);
		String username = null;
		String authToken = null;
		// 如果请求头有JWT代表已经通过验证，可以直接挖出username
		if (requestHeader != null) {
			authToken = requestHeader;
			try {
				username = jwtTokenUtil.getUserIdFromToken(authToken);
			} catch (final ExpiredJwtException e) {
			}
		}

		// 如果上下文中没有此用户那么就添加此用户。
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			RedisUserDetails redisUserDetails = this.redisUserUtil.getUserDetailsByUserId(username);
			if (redisUserDetails != null) {
				// 把redis中的权限转换成security的格式
				Collection<? extends GrantedAuthority> authorities = securityUtil
						.listStrToCollecSimAuth(redisUserDetails.getAuthorities());
				// 使用jwt中放置的username获取到它对应的角色或者其他信息。
				if (jwtTokenUtil.validateToken(authToken, redisUserDetails)) {
					final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							new UserExDetails(username, authorities), null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		chain.doFilter(request, response);
	}

	
}