package com.rainforest.model.user.registration;

import com.rainforest.core.GUID;
import com.rainforest.model.user.UserInfo;
import com.rainforest.model.user.seller.SellerInfo;

public class SellerRegistrationRequest extends UserRegistrationRequest {
	private UserInfo userInfo;
	private SellerInfo sellerInfo;

	public SellerRegistrationRequest(String email, String password, String username) {
		userInfo = new UserInfo(new GUID(), email, password, username);
	}
	
	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
}
