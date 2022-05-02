package com.rainforest.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.rainforest.core.GUID;
import com.rainforest.factories.Factory;
import com.rainforest.model.LoginResponse;
import com.rainforest.model.ModifyResponse;
import com.rainforest.model.RainforestModel;
import com.rainforest.model.user.User;
import com.rainforest.model.user.buyer.Buyer;
import com.rainforest.model.user.seller.Seller;

public class Controller {

	private RainforestModel model;
	private Factory<User> userFactory;

	public Controller(Factory<User> userFactory) {
		model = new RainforestModel();

		this.userFactory = userFactory;
	}

	public boolean addBuyer(Buyer buyer) {
		return model.addBuyer(buyer);
	}

	public boolean addSeller(Seller seller) {
		return model.addSeller(seller);
	}

	public boolean doesUserExist(String email) {
		return model.doesUserExist(email);
	}

	public LoginResponse tryLoginAsBuyer(String email, String password) {
		return model.tryLoginAsBuyer(email, password);
	}

	public LoginResponse tryLoginAsSeller(String email, String password) {
		return model.tryLoginAsSeller(email, password);
	}

	public boolean removeUser(GUID guid) {
		return model.removeUser(guid);
	}

	public void deserializeModel(InputStream in) {
		JSONArray usuarios = new JSONObject(new JSONTokener(in)).getJSONArray("usuarios");

		for (int i = 0; i < usuarios.length(); i++) {
			JSONObject userJO = usuarios.getJSONObject(i);

			User u = this.userFactory.createInstance(userJO);

			// THIS IS BAD, VERY VERY BAD.
			if (userJO.has("buyer_info"))
				model.addBuyer((Buyer) u);
			else if (userJO.has("seller_info"))
				model.addSeller((Seller) u);
		}
	}

	public void serializeModel(OutputStream os) {
		new PrintWriter(os).write(model.serialize().toString());
	}

	public User getCurrentUser() {
		return model.getCurrentUser();
	}

	public GUID getUserGUIDWithAuthentication(String email, String password) {
		return model.getUserGUIDWithAuthentication(email, password);
	}

	public ModifyResponse tryModifyUser(String email, String username, String password, String address, int telephone,
			String dni) {
		return model.tryModifyBuyer(email, username, password, address, telephone, dni);
	}

	public JSONArray getProducts(GUID guid) {
		return model.getProducts(guid);
	}

//	public void removePrdouct(GUID name, String dni) {
//		// TODO Auto-generated method stub
//		model.removeProduct(name, dni);
//	}

}
