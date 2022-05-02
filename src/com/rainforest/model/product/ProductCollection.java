package com.rainforest.model.product;

public class ProductCollection {

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
}
