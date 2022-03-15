package com.rainforest.model.user.registration;

import com.rainforest.core.GUID;
import com.rainforest.model.user.seller.SellerInfo;

public class SellerRegistrationRequest extends UserRegistrationRequest {
	private SellerInfo sellerInfo;

	public SellerRegistrationRequest(String name, String email) {
		sellerInfo = new SellerInfo(name, new GUID(), email);
	}
	
	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}

	@Override
	protected String getEmail() {
		return sellerInfo.getEmail();
	}
	
	
}
