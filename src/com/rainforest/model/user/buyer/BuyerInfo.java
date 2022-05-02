package com.rainforest.model.user.buyer;

import org.json.JSONObject;

import com.rainforest.model.JSONSerializable;

public class BuyerInfo implements JSONSerializable {

	private String DNI;

	public BuyerInfo(String dNI) {
		DNI = dNI;
	}

	public String getDNI() {
		return DNI;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();

		jo.put("DNI", DNI);

		return jo;
	}
}
