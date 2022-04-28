package com.rainforest.model.user.seller;

import org.json.JSONObject;

import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;

public class Seller extends User {

	private SellerInfo sellerInfo;
	private UserInfo u;

	public Seller(UserInfo userInfo, SellerInfo sellerInfo) {
		super(userInfo);
		this.u = userInfo;
		this.sellerInfo = sellerInfo;
	}

	

	
	public JSONObject getSellerInfo() {
		JSONObject salida = new JSONObject();
		
		salida.put("email", u.getEmail());
		salida.put("password", u.getPassword());
		salida.put("username", u.getUsername());
		salida.put("GUID", u.getUserID());
		salida.put("direction",u.getDir());
		salida.put("tel",Integer.toString(u.getTel()));
		salida.put("dni",u.getDNI());
		salida.put("RFC", this.sellerInfo.getRfc());
		return salida;
	}
	
	@Override
	public boolean canSell() {
		return true;
	}
	
	@Override
	public boolean canBuy() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public JSONObject getBuyerInfo() {
		// TODO Auto-generated method stub
		return null;
	}



	
}
