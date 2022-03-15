package com.rainforest.model;

import com.rainforest.model.user.seller.Seller;

public class Controller {
	private Model model;
	
	public Controller() {
		model = new Model();
	}
	
	public boolean addSeller(Seller seller) {
		return model.addSeller(seller);
	}
	
	public boolean removeSeller(Seller seller) {
		return model.removeSeller(seller);
	}
	
	public boolean createSellerRegistrationRequest(String name, String email) {
		return model.createSellerRegistrationRequest(name, email);
	}
}
