package com.rainforest.model.user.seller;

import com.rainforest.model.user.User;

public class Seller extends User {

	private SellerInfo info;

	public Seller(SellerInfo info) {
		this.info = info;
	}

	public SellerInfo getSellerInfo() {
		return this.info;
	}

	@Override
	public int hashCode() {
		return info.hashCode();
	}

}
