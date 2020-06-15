package com.butch.securityzuul6101.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.butch.apiutils.jwt.JwtProperties;
import com.butch.apiutils.jwt.JwtTokenUtil;
import com.butch.apiutils.redis.RedisUserUtil;
import com.butch.securityzuul6101.security.JwtAuthorizationTokenFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtProperties jwtProperties;
	@Autowired
	private RedisUserUtil redisUserUtil;
	@Autowired
	private SecurityUtil securityUtil;
	
	/**
	 * 建造一个没有自动注入的Token过滤器，目前版本过滤器只要自动注入就会自动装配至mvc，不会进入security链过滤静态资源。
	 * @param strs
	 * @return
	 */
	public JwtAuthorizationTokenFilter getJwtAuthorizationTokenFilter(){
		return new JwtAuthorizationTokenFilter(jwtTokenUtil, jwtProperties, redisUserUtil, securityUtil);
	}


	/**
	 * 把redis缓存中的List<String>权限转换成Seciruty的支持类型
	 * @return
	 */
	public Collection<? extends GrantedAuthority> listStrToCollecSimAuth(List<String> strs){
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for(String str: strs) {
			authorities.add(new SimpleGrantedAuthority(str));
		}
		return authorities;
	}
	
	public List<String> collecSimAuthToListStr(Collection<? extends GrantedAuthority> authorities){
		List<String> listStr=new ArrayList<String>();
		for(GrantedAuthority authority:authorities) {
			listStr.add(authority.toString());
		}
		return listStr;
	}
}
