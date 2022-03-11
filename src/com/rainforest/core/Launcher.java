package com.rainforest.core;

import com.rainforest.model.Controller;
import com.rainforest.model.seller.Seller;
import com.rainforest.model.seller.SellerInfo;

public class Launcher {

	public static void main(String[] args) {
		Controller controller = new Controller();
		
		controller.addSeller(new Seller(new SellerInfo("Media Markt", new GUID(), "info@mediamarkt.es")));

	}

}
