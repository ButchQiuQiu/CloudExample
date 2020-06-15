package com.butch.securityzuul6101.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.butch.apiutils.pojo.User;
import com.butch.securityzuul6101.util.SecurityUtil;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserExDetails implements UserDetails {
	
	/**
	 * 此类为pojo，不好使用容器。工具类直接静态化使用
	 */
	private static SecurityUtil securityUtil=new SecurityUtil();
	
	private Collection<? extends GrantedAuthority> authorities;
	private User user=new User();
	private static final long serialVersionUID = 1L;
	
	public UserExDetails(User user) {
		this.user=user;
		List<SimpleGrantedAuthority> simpleGrantedAuthority = new ArrayList<>();
		simpleGrantedAuthority.add(new SimpleGrantedAuthority(user.getFk_depart().getFk_authority().getName()));
		this.setAuthorities(simpleGrantedAuthority);
	}

	public UserExDetails(String username,Collection<? extends GrantedAuthority> authorities) {
		this.user=new User(username);
		this.setAuthorities(authorities);
	}

	// 获取权限名
	@SuppressWarnings("all")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	//设置权限 
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities=authorities;
		this.user.setAuthorities(securityUtil.collecSimAuthToListStr(this.authorities));
	}

	// 获取密码
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// 获取账号
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/**
	 * 账号是否过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 是否禁用
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 密码是否过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 是否启用
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
