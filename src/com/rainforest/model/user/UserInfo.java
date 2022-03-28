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
