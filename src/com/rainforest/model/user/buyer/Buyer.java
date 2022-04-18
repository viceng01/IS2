package com.rainforest.model.user.buyer;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;

public class Buyer extends User {

	private BuyerInfo buyerInfo;
	

	public Buyer(UserInfo userInfo, BuyerInfo buyerInfo) {
		super(userInfo);
		
		this.buyerInfo = buyerInfo;
	}

	

	public BuyerInfo getBuyerInfo() {
		return this.buyerInfo;
	}
	
	@Override
	public boolean canBuy() {
		return true;
	}
	
	@Override
	public boolean canSell() {
		return false;
	}

	@Override
	public int hashCode() {
		return buyerInfo.hashCode();
	}

}
