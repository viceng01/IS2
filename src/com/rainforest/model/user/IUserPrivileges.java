package com.rainforest.model.user;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IUserPrivileges {
	public boolean canSell();
	public boolean canBuy();
	public JSONObject getBuyerInfo();
	public JSONObject getSellerInfo();
}
