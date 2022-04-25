package com.rainforest.model.product;

import java.util.Collection;
import java.util.Map;

import com.rainforest.core.GUID;

public class Catalogue {
	private Map<GUID, ProductCollection> products;

	public Collection<ProductCollection> getAllProductCollections() {
		return products.values();
	}

	public boolean containsProduct(Product p) {
		return products.containsKey(p.getGUID());
	}

	public void addProduct(Product p, int amount) throws IllegalArgumentException {
		if(amount <= 0)
			throw new IllegalArgumentException();
		
		ProductCollection newCtx = new ProductCollection(p, amount);
		ProductCollection previousCtx = products.putIfAbsent(p.getGUID(), newCtx);

		int prevNumProducts = 0;

		if (previousCtx != null)
			prevNumProducts = previousCtx.getAmount();

		newCtx.setAmount(prevNumProducts + amount);
	}

	public void removeProduct(Product p, int amount) throws IllegalArgumentException {
		if(amount <= 0)
			throw new IllegalArgumentException();
		
		GUID productGUID = p.getGUID();
		ProductCollection ctx = products.getOrDefault(productGUID, null);

		if (ctx == null)
			return;

		int remainder = ctx.getAmount() - amount;
		
		ctx.setAmount(remainder);
		
		if(remainder <= 0)
			products.remove(productGUID);
	}

	public void removeProductCollection(Product p) {
		products.remove(p.getGUID());
	}
}
