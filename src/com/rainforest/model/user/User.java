package com.rainforest.model.user;

import org.json.JSONObject;

public abstract class User implements IUserPrivileges {

	private UserInfo userInfo;

	public User(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public JSONObject getJson() {
		JSONObject jo1 = new JSONObject();
		jo1.put("GUID",userInfo.getUserID());
		jo1.put("email", userInfo.getEmail()); 
		jo1.put("password", userInfo.getPassword());
		jo1.put("username", userInfo.getUsername());
		return jo1;
	}
}
