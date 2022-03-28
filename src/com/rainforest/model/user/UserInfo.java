package com.rainforest.model.user;

import com.rainforest.core.GUID;

public class UserInfo {
	private GUID userID;
	private String email;
	private String password;
	
	public UserInfo(GUID userID, String email, String password) {
		this.userID = userID;
		this.email = email;
		this.password = password;
	}
	
	public GUID getUserID() {
		return userID;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	
}
