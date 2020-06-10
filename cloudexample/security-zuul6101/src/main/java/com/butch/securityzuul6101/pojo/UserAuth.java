package com.butch.securityzuul6101.pojo;

import java.util.Collection;

import com.butch.apiutils.pojo.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuth extends User implements UserDetails{

    private static final long serialVersionUID = 1L;

    private Collection<? extends GrantedAuthority> authorities;



	public UserAuth(String username, Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
		this.setUsername(username);
	}

	public UserAuth(String username,String password,Collection<? extends GrantedAuthority> authorities){
		this.setUsername(username);
		this.setPassword(password);
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
		return this.getPassword();
	}

	// 获取账号
	@Override
	public String getUsername() {
		return this.getUsername();
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


	@Override
	public String toString() {
		return "UserAuth [authorities=" + authorities + ", password=" + this.getPassword() + ", username=" + this.getUsername()+ "]";
	}
    
}