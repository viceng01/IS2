package com.rainforest.model.user.admin;

import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;

public class Admin extends User {

	public Admin(UserInfo userInfo) {
		super(userInfo);
	}

	@Override
	public boolean canViewRegistrationRequests() {
		return true;
	}

	@Override
	public boolean canProcessRegistrationRequests() {
		return true;
	}

	@Override
	public boolean canDeleteRegistrationRequests() {
		return true;
	}
}
