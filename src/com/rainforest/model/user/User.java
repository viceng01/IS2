package com.rainforest.model.user;

public abstract class User implements IUserPrivileges {

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
	
}
