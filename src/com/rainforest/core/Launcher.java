package com.rainforest.core;

import com.rainforest.model.Controller;

public class Launcher {

	public static void main(String[] args) {
		Controller controller = new Controller();

		boolean addedJohn = controller.createSellerRegistrationRequest("John", "my.email@somewhere.com");
		boolean addedMary = controller.createSellerRegistrationRequest("Mary", "my.email@somewhere.com");
	
		System.out.println("Added John: " + addedJohn + ", Added Mary: " + addedMary);
	}

}
