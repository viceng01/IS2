package com.rainforest.model.user.seller;

import com.rainforest.core.GUID;

public class SellerInfo {

	private String name;
	private GUID sellerID;
	private String email;

	public SellerInfo(String name, GUID sellerID, String email) {
		this.name = name;
		this.sellerID = sellerID;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public GUID getSellerID() {
		return sellerID;
	}

	public String getEmail() {
		return email;
	}
	
	
	@Override
	public int hashCode() {
		return sellerID.hashCode();
	}
	
	
}
