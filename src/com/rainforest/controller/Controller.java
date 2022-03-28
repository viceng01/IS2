package com.rainforest.controller;

import com.rainforest.model.RainforestModel;
import com.rainforest.model.user.User;
import com.rainforest.model.user.registration.UserRegistrationRequest;

public class Controller {
	private RainforestModel model;
	
	public Controller() {
		model = new RainforestModel();
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

	public boolean tryLogin(String email, String password) {
		return model.tryLogin(email, password);
	}
}
