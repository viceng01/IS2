package com.rainforest.model.user;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.product.Product;

public interface IUserPrivileges {
	public boolean canSell();
	public boolean canBuy();
	public JSONObject getBuyerInfo();
	public JSONObject getSellerInfo();
	public void removeProduct(GUID name);
	
}
