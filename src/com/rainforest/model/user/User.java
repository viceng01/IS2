package com.rainforest.model.user;

public abstract class User implements IUserPrivileges {

	private UserInfo userInfo;

	public User(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
}
