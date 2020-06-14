package com.butch.securityzuul6101.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.butch.apiutils.jwt.JwtProperties;
import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.pojo.User;
import com.butch.apiutils.redis.RedisUserUtil;
import com.butch.apiutils.redis.pojo.RedisUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
/**
 * 自定义拦截器，把用户的token添加进上下文中，可以让后面的鉴权之类的拦截器使用到此用户。
 */
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisUserUtil redisUserUtil;


    // 获取用户信息并且放入上下文中。鉴权可以直接在上下文中取得权限鉴权。
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain chain) throws ServletException, IOException {
        // final String requestHeader = request.getHeader(jwtProperties.getToken());
        String requestHeader=null;
        for(Cookie cookie:request.getCookies()){
            if(jwtProperties.getToken().equals(cookie.getName()) ){
                requestHeader=cookie.getValue();
            }
        }
        System.out.println("-------jwt："+requestHeader);
        String username = null;
        String authToken = null;
        //如果请求头有JWT代表已经通过验证，可以直接挖出username
        if (requestHeader != null) {
            authToken = requestHeader;
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (final ExpiredJwtException e) {
            }
        }
        //如果上下文中没有此用户那么就添加此用户。
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("JWTusername:"+username);
            // 使用jwt中放置的username获取到它对应的角色或者其他信息。
             RedisUserDetails redisUserDetails = this.redisUserUtil.getUserDetailsByUsername(username);
            if (jwtTokenUtil.validateToken(authToken,redisUserDetails)) {
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        new User(username,redisUserDetails.getAuthorities()), null, redisUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}