package com.rainforest.model;

import com.rainforest.model.user.User;
import com.rainforest.model.user.registration.UserRegistrationRequest;

public class Controller {
	private Model model;
	
	public Controller() {
		model = new Model();
	}
	
	public boolean addUser(User user) {
		return model.addUser(user);
	}
	
	public boolean removeUser(User user) {
		return model.removeUser(user);
	}
	
	public boolean addUserRegistrationRequest(UserRegistrationRequest urr) {
		return model.addUserRegistrationRequest(urr);
	}
}
