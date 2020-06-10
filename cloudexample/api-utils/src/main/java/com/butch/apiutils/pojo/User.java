package com.butch.apiutils.pojo;

import java.io.Serializable;

/**
 * 用户表实现UserDetails接口
 */
public class User implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 123123;

	private String username;
	private String password;

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	
	

}
