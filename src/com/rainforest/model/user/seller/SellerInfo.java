package com.rainforest.model.user.seller;

import java.util.Collection;

import com.rainforest.model.product.Catalogue;
import com.rainforest.model.product.Product;
import com.rainforest.model.product.ProductCollection;

public class SellerInfo {

	private Catalogue catalogue;

	public SellerInfo() {
		catalogue = new Catalogue();
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
}
