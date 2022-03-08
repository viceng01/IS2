package com.rainforest.model;

import java.util.HashSet;
import java.util.Set;

import com.rainforest.model.seller.Seller;

public class Model {

	private Set<Seller> sellerSet;

	public Model() {
		sellerSet = new HashSet<>();
	}
	
	public boolean addSeller(Seller seller) {
		if(seller == null) {
			return false;
		}
		return sellerSet.add(seller);
	}
	
	public boolean removeSeller(Seller seller) {
		if(seller == null) {
			return false;
		}
		
		return sellerSet.remove(seller);
	}
	
}
