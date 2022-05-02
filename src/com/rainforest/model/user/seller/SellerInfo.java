package com.rainforest.model.user.seller;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.JSONSerializable;
import com.rainforest.model.product.Catalogue;
//import com.rainforest.model.product.Catalogue;
import com.rainforest.model.product.Product;
//import com.rainforest.model.product.ProductCollection;
import com.rainforest.model.product.ProductCollection;

public class SellerInfo implements JSONSerializable {

	private String rfc;
	private String CIF;
	private Catalogue catalogue;

	public SellerInfo(Catalogue c, String CIF, String rfc) {
		catalogue = c;
		this.CIF = CIF;
		this.rfc = rfc;
	}

	public void removeProductByName(GUID name) {
		catalogue.removeProductByName(name);
	}

	public void addProductToCatalogue(Product p) {
		catalogue.addProduct(p, 1);
	}

	public void removeProductFromCatalogue(Product p) {
		catalogue.removeProductCollection(p);
	}

	public Collection<ProductCollection> getAllProductCollections() {
		return catalogue.getAllProductCollections();
	}

	public String getCIF() {
		return CIF;
	}

	public String getRfc() {
		return rfc;
	}

	public Catalogue getCatalogue() {
		return this.catalogue;
	}
	/*
		
		*/

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();

		jo.put("CIF", CIF);
		jo.put("rfc", rfc);
		jo.put("catalogue", catalogue.serialize());

		return jo;
	}

	public JSONArray serializeProducts() {
		return catalogue.serialize().getJSONArray("product_collections");
	}
}
