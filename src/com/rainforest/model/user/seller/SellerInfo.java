package com.rainforest.model.user.seller;

import java.util.Collection;


//import com.rainforest.model.product.Catalogue;
import com.rainforest.model.product.Product;
//import com.rainforest.model.product.ProductCollection;

public class SellerInfo {
	
	private String dir;
	private String DNI;
	private int tel;
	private String rfc;
	
	public SellerInfo() {
		//catalogue = new Catalogue();
	}
	public SellerInfo(String dir, String DNI, int telefono, String rfc) {
		this.dir = dir;
		this.DNI= DNI;
		this.tel = telefono;
		this.rfc = rfc;
	}

	public String getDir() {
		return dir;
	}
	public String getRfc() {
		return rfc;
	}

	public String getDNI() {
		return DNI;
	}

	public int getTel() {
		return tel;
	}
/*
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
	*/
}
