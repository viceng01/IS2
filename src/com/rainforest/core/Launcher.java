package com.rainforest.core;

import com.rainforest.model.Controller;
import com.rainforest.model.user.registration.SellerRegistrationRequest;

public class Launcher {

	public static void main(String[] args) {
		Controller controller = new Controller();

		boolean addedJohn = controller.addUserRegistrationRequest(new SellerRegistrationRequest( "John", "my.email@somewhere.com"));
		boolean addedMary = controller.addUserRegistrationRequest(new SellerRegistrationRequest( "Mary", "my.email@somewhere.com"));
		
		System.out.println("Added John: " + addedJohn + ", Added Mary: " + addedMary);
	}

}
