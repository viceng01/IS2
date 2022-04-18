package com.rainforest.model.user;

import com.rainforest.core.GUID;

public class UserInfo {
	private GUID userID;
	private String email;
	private String password;
	private String username;
	
	
	public UserInfo(GUID userID, String email, String password, String username) {
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	public UserInfo(UserInfo u) {
		this.userID = u.userID;
		this.email = u.email;
		this.password = u.password;
		this.username = u.username;
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public GUID getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
}
