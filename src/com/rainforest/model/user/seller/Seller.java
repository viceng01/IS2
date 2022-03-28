package com.rainforest.model.user.seller;

import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;

public class Seller extends User {

	private SellerInfo sellerInfo;

	public Seller(UserInfo userInfo, SellerInfo sellerInfo) {
		super(userInfo);
		
		this.sellerInfo = sellerInfo;
	}

	public SellerInfo getSellerInfo() {
		return this.sellerInfo;
	}

	@Override
	public int hashCode() {
		return sellerInfo.hashCode();
	}

}
