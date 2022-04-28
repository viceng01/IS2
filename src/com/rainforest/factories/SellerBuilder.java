package com.rainforest.factories;

import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;
import com.rainforest.model.user.seller.Seller;
import com.rainforest.model.user.seller.SellerInfo;


public class SellerBuilder extends Builder<User>{

	
	public SellerBuilder() {
		super("seller");
	}


	@Override
	protected User createTheInstance(JSONObject data) {
		GUID g;
		if (data.getString("GUID").equals(""))
			g= new GUID();
		else 
			g = new GUID(data.getString("GUID"));
		
		String email = data.getString("email");
		String password = data.getString("password");
		String username = data.getString("username");
		
		UserInfo u = new UserInfo (g,email,password,username);
		String dir = data.getString("direction");
		String dni = data.getString("dni");
		int tel = data.getInt("tel");
		String rfc;
		if (data.has("RFC"))
			rfc = data.getString("RFC");
		else
			rfc = "";
		SellerInfo b = new SellerInfo(dir,dni,tel,rfc);
		
		return new Seller (u,b);
	}

}
