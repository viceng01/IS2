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

	
	
	@Override
	public boolean canBuy() {
		return true;
	}
	
	@Override
	public boolean canSell() {
		return false;
	}
	
	public String getBuyerInfo() {
		String salida;
		salida = this.buyerInfo.getDNI() + " " + Integer.toString(this.buyerInfo.getTel());
		return salida;
	}
	

	@Override
	public int hashCode() {
		return buyerInfo.hashCode();
	}

}
