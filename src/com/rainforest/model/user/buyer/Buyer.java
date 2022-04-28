package com.rainforest.model.user.buyer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;

public class Buyer extends User {

	private BuyerInfo buyerInfo;
	private UserInfo u;

	public Buyer(UserInfo userInfo, BuyerInfo buyerInfo) {
		super(userInfo);
		this.u = userInfo;
		this.buyerInfo = buyerInfo;
	}

	
	
	@Override
	public boolean canBuy() {
		return true;
	}
	
	@Override
	public boolean canSell() {
		return false;
	}
	
	public JSONObject getBuyerInfo() {
		JSONObject salida = new JSONObject();
		salida.put("email", u.getEmail());
		salida.put("password", u.getPassword());
		salida.put("username", u.getUsername());
		salida.put("GUID", u.getUserID());
		salida.put("direction",this.buyerInfo.getDir());
		salida.put("tel",Integer.toString(this.buyerInfo.getTel()));
		salida.put("dni",this.buyerInfo.getDNI());
		return salida;
	}
	

	@Override
	public int hashCode() {
		return buyerInfo.hashCode();
	}



	
	@Override
	public JSONObject getSellerInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
