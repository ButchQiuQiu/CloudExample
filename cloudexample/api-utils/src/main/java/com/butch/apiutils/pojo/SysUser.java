package com.butch.apiutils.pojo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户表实现UserDetails接口
 */
public class SysUser implements UserDetails {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;



	public SysUser(String username, Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
		this.username = username;
	}

	public SysUser(String username,String password,Collection<? extends GrantedAuthority> authorities){
		this.username = username;
		this.password=password;
		this.authorities = authorities;
	}

	// 获取权限名
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		// simpleGrantedAuthorities.add(new SimpleGrantedAuthority(this.fk_depart.getFk_authority().getName()));
		return this.authorities;
	}

	// 获取密码
	@Override
	public String getPassword() {
		return this.password;
	}

	// 获取账号
	@Override
	public String getUsername() {
		return this.username;
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

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SysUser [authorities=" + authorities + ", password=" + password + ", username=" + username + "]";
	}
}
