package com.rainforest.model.user.admin;

import com.rainforest.model.user.User;

public class Admin extends User {

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
