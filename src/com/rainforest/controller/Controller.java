package com.rainforest.controller;

import java.io.InputStream;
import java.sql.Struct;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.rainforest.factories.Factory;
import com.rainforest.model.RainforestModel;
import com.rainforest.model.user.User;
import com.rainforest.model.user.registration.UserRegistrationRequest;
import com.rainforest.model.user.seller.Seller;
import com.rainforest.view.LoginResponse;


public class Controller {
	private RainforestModel model;
	private Factory<User> userFactory;
	private String claveUs[] = {"type","data","password","username"};
	private String guid;
	private String email;
	private String passw;
	private String user;
	private JSONObject data;
	private String type;
	
	

	public Controller(Factory<User> userFactory) {
		this.userFactory = userFactory;
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
	
	public void loadDataBase(InputStream in) {
		
        JSONObject jo = new JSONObject(new JSONTokener(in));
        JSONArray usuarios = jo.getJSONArray("usuarios");
        
        for(int i = 0; i < usuarios.length(); i++) {
        	JSONObject o = usuarios.getJSONObject(i);
        	User u = this.userFactory.createInstance(o);
        	
        	this.addUser(u);
            
        }
        
	}
}
