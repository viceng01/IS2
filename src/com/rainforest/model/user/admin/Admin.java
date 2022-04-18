package com.rainforest.model.user.admin;

import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;

public class Admin extends User {

	public Admin(UserInfo userInfo) {
		super(userInfo);
	}

	@Override
	public boolean canSell() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuy() {
		// TODO Auto-generated method stub
		return false;
	}

}
