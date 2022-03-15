package com.rainforest.model;

import com.rainforest.model.seller.Seller;

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
}
