package com.rainforest.model.user;

public abstract class User implements IUserPrivileges {

	private UserInfo userInfo;

	public User(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public User() {
		
	}

	@Override
	public boolean canViewRegistrationRequests() {
		return false;
	}

	@Override
	public boolean canProcessRegistrationRequests() {
		return false;
	}

	@Override
	public boolean canDeleteRegistrationRequests() {
		return false;
	}
	

	@Override
	public boolean canSell() {
		return false;
	}
	
	@Override
	public boolean canBuy() {
		return false;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
}
