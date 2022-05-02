package com.rainforest.model.product;

import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.JSONSerializable;

public class Product implements JSONSerializable{

	private GUID guid;
	private String name;
	private String description;
	private float price;

	public Product(GUID guid, String name, String description, float price) {
		this.guid = guid;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public GUID getGUID() {
		return guid;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();
		jo.put("GUID", guid.toString());
		jo.put("name", name);
		jo.put("description", description);
		jo.put("price", price);
		return jo;
	}
}
