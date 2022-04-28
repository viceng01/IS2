package com.rainforest.factories;

import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;
import com.rainforest.model.user.buyer.Buyer;
import com.rainforest.model.user.buyer.BuyerInfo;
import com.rainforest.model.user.buyer.PaymentMethod;


public class BuyerBuilder extends Builder<User>{

	PaymentMethod metodoPago;
	
	public BuyerBuilder() {
		super("buyer");
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
		
		String dir = data.getString("direction");
		String dni = data.getString("dni");
		int tel = data.getInt("tel");
		UserInfo u = new UserInfo (g,email,password,username,dir,dni,tel);
		/*TODO*/
		//De momento no nos hace falta !!!
		
		BuyerInfo b = new BuyerInfo();
		
		return new Buyer (u,b);
	}

}
