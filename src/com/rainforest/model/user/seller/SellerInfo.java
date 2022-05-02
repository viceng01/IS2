package com.rainforest.model.user.seller;

import java.util.Collection;

import com.rainforest.model.product.Catalogue;
//import com.rainforest.model.product.Catalogue;
import com.rainforest.model.product.Product;
//import com.rainforest.model.product.ProductCollection;
import com.rainforest.model.product.ProductCollection;

public class SellerInfo {
	
	private String rfc;
	
	private Catalogue catalogue;

	
	public void addProductToCatalogue(Product p) {
		catalogue.addProduct(p, 1);
	}
	public void removeProductFromCatalogue(Product p) {
		catalogue.removeProductCollection(p);
	}
	public Collection<ProductCollection> getAllProductCollections() {
		return catalogue.getAllProductCollections();
	}
	
	public SellerInfo() {
		catalogue= new Catalogue();
	}
	public SellerInfo(String rfc) {
		this.rfc = rfc;
	}

	public String getRfc() {
		return rfc;
	}

/*
	
	*/
}
