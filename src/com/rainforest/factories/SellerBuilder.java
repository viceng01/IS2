package com.rainforest.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.product.Catalogue;
import com.rainforest.model.product.Product;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;
import com.rainforest.model.user.seller.Seller;
import com.rainforest.model.user.seller.SellerInfo;

public class SellerBuilder extends Builder<User> {

	private Catalogue catalogue;

	public SellerBuilder() {
		super("seller");
	}

	@Override
	protected User createTheInstance(JSONObject data) {
		GUID g;
		
		if (data.getString("GUID").equals(""))
			g = new GUID();
		else
			g = new GUID(data.getString("GUID"));

		String email = data.getString("email");
		String password = data.getString("password");
		String username = data.getString("username");

		String dir = data.getString("direction");
		String dni = data.getString("dni");
		int tel = data.getInt("tel");
		
		UserInfo u = new UserInfo(g, email, password, username, dir, tel);
		
		String rfc = "";
		String cif = data.getString("CIF");
		
		if (data.has("RFC"))
			rfc = data.getString("RFC");
		
		catalogue = new Catalogue();
		
		if (data.has("products")) {
			JSONArray ja = data.getJSONArray("products");
			
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				JSONObject jo1 = jo.getJSONObject("product");
				
				if (jo1.getString("GUID").equals(""))
					g = new GUID();
				else
					g = new GUID(jo1.getString("GUID"));
				
				String nom = jo1.getString("name");
				String desc = jo1.getString("description");
				float price = jo1.getFloat("price");
				int amount = jo.getInt("amount");
				
				Product p = new Product(g, nom, desc, price);

				catalogue.addProduct(p, amount);
			}
		}
		
		SellerInfo b = new SellerInfo(catalogue, cif, rfc);

		return new Seller(u, b);
	}

}
