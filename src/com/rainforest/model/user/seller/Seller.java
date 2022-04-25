package com.rainforest.model.user.seller;

import com.rainforest.core.GUID;
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
	public boolean canSell() {
		return true;
	}
	
	@Override
	public boolean canBuy() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int hashCode() {
		return sellerInfo.hashCode();
	}



	@Override
	public String getBuyerInfo() {
		// TODO Auto-generated method stub
		return null;
	}



	
}
