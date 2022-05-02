package com.rainforest.model.product;

import org.json.JSONObject;

import com.rainforest.model.JSONSerializable;

public class ProductCollection implements JSONSerializable{

	private Product product;
	private int amount;

	public ProductCollection(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setProduct(Product p) {
		this.product = p;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();
		jo.put("amount", amount);
		jo.put("product",product.serialize());
		return jo;
	}
}
