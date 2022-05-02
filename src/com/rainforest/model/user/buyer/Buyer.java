package com.rainforest.model.user.buyer;

import org.json.JSONObject;

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

	public BuyerInfo getBuyerInfo() {
		return buyerInfo;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = super.serialize();

		jo.put("buyer_info", buyerInfo.serialize());

		return jo;
	}

	@Override
	public int hashCode() {
		return buyerInfo.hashCode();
	}

	@Override
	public void removeProduct(GUID name) {
		// TODO Auto-generated method stub

	}

}
