package com.rainforest.model.user.seller;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;

public class Seller extends User {

	private SellerInfo sellerInfo;

	public Seller(UserInfo userInfo, SellerInfo sellerInfo) {
		super(userInfo);
		this.sellerInfo = sellerInfo;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = super.serialize();

		jo.put("seller_info", sellerInfo.serialize());

		return jo;
	}

	public JSONArray serializeProducts() {
		return sellerInfo.serializeProducts();
	}

	public SellerInfo getSellerInfo() {
		return sellerInfo;
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
	public void removeProduct(GUID name) {
		sellerInfo.removeProductByName(name);

	}

}
