package com.rainforest.model.product;

import com.rainforest.core.GUID;

public abstract class Product {

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
}
