package com.rainforest.model.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.JSONSerializable;

public class Catalogue implements JSONSerializable {
	private Map<GUID, ProductCollection> products;

	public Collection<ProductCollection> getAllProductCollections() {
		return products.values();
	}

	public boolean containsProduct(Product p) {
		return products.containsKey(p.getGUID());
	}

	public void addProduct(Product p, int amount) throws IllegalArgumentException {
		if (amount <= 0)
			throw new IllegalArgumentException();
		if (products == null)
			products = new HashMap<GUID, ProductCollection>();

		ProductCollection newCtx = new ProductCollection(p, amount);
		ProductCollection previousCtx = products.put(p.getGUID(), newCtx);

		int prevNumProducts = 0;

		if (previousCtx != null)
			prevNumProducts = previousCtx.getAmount();

		newCtx.setAmount(prevNumProducts + amount);
	}

	public void removeProduct(Product p, int amount) throws IllegalArgumentException {
		if (amount <= 0)
			throw new IllegalArgumentException();

		GUID productGUID = p.getGUID();
		ProductCollection ctx = products.getOrDefault(productGUID, null);

		if (ctx == null)
			return;

		int remainder = ctx.getAmount() - amount;

		ctx.setAmount(remainder);

		if (remainder <= 0)
			products.remove(productGUID);
	}

	public void removeProductCollection(Product p) {
		products.remove(p.getGUID());
	}

	public void removeProductByName(GUID id) {
		products.remove(id);

	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();

		products.values().forEach((pc) -> {
			ja.put(pc.serialize());
		});

		jo.put("product_collections", ja);

		return jo;
	}
}
