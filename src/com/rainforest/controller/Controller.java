package com.rainforest.controller;

import com.rainforest.model.RainforestModel;
import com.rainforest.model.user.User;
import com.rainforest.model.user.registration.UserRegistrationRequest;
import com.rainforest.view.LoginResponse;

public class Controller {
	private RainforestModel model;
	
	public Controller() {
		model = new RainforestModel();
	}
	
	public boolean addUser(User user) {
		return model.addUser(user);
	}
	
	public boolean addUserRegistrationRequest(UserRegistrationRequest urr) {
		return model.addUserRegistrationRequest(urr);
	}

	public boolean doesUserExist(String email) {
		return model.doesUserExist(email);
	}
	
	public void registerBuyer(String email, String password, String username) {
		model.registerBuyer(email, password, username);
	}
	
	public void registerSeller(String email, String password, String username) {
		model.registerSeller(email, password, username);
	}
	
	public boolean removeUser(User user) {
		return model.removeUser(user);
	}

	public LoginResponse tryLogin(String email, String password,String type) {
		return model.tryLogin(email, password,type);
	}
}
