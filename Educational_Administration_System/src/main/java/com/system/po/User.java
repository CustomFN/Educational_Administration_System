package com.system.po;

public class User {

	private String userId;
	private String username;
	private String password;
	private Integer role;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public User() {
		super();
	}
	public User(String userId) {
		super();
		this.userId = userId;
	}
	
	
}
