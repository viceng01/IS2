package com.rainforest.model.user.seller;

public class Seller {

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
