package com.butch.apiutils.pojo;

public class UserTest {
	private String username;
	private String password;
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
	public UserTest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public UserTest() {
		super();
	}
	@Override
	public String toString() {
		return "UserTest [username=" + username + ", password=" + password + "]";
	}
	
}
