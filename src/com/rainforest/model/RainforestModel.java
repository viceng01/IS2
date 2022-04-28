package com.rainforest.model;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;
import com.rainforest.model.user.buyer.Buyer;
import com.rainforest.model.user.buyer.BuyerInfo;
import com.rainforest.model.user.seller.Seller;
import com.rainforest.model.user.seller.SellerInfo;
import com.rainforest.view.LoginResponse;

/**
 * @author Alex
 *
 */
public class RainforestModel {

	private Set<User> userSet;


	public RainforestModel() {
		userSet = new HashSet<>();

	}

	/**
	 * Attempts to add a user to the model. Returns true if the user could be added
	 * correctly. Returns false if the user was already in the model, if the user
	 * was null or any other case.
	 * 
	 * @param user : The user to add
	 * @return <b>true</b> if the user was added, <b>false</b> otherwise
	 */
	public boolean addUser(User user) {
		if (user == null) {
			return false;
		}
		return userSet.add(user);
	}

	/**
	 * Attempts to remove a user from the model. Returns true if the user was
	 * previously present in the model and could be removed correctly. Returns false
	 * if the user was not present in the model, if the user to remove was null or
	 * any other case.
	 * 
	 * @param user : The user to remove
	 * @return <b>true</b> if the user was removed, <b>false</b> otherwise
	 */
	public boolean removeUser(User user) {
		if (user == null) {
			return false;
		}
		return userSet.remove(user);
	}

	public LoginResponse tryLogin(String email, String password, String type) {
		for (User user : userSet) {
			UserInfo userInfo = user.getUserInfo();

			if (userInfo.getEmail().equals(email)) {
				//Si esta bien seleccionada la categoria con el tipo de usuario
				//se comprueba el resto de datos y sino es que no esta el usuario
				if (type.equals(tipoUsuario(user)))
					return (userInfo.getPassword().equals(password)) ? LoginResponse.OK : LoginResponse.INCORRECT_PASSWORD;
			}
		}

		return LoginResponse.UNKNOWN_USER;
	}
	
	

	public String tipoUsuario (User u) {
		if (u.canBuy())
			return "Buyer";
		if (u.canSell())
			return "Seller";
		
		return "";
	}
	
	
	public LoginResponse doesUserExist(String email, String pasw, String u) {
		if (email.equals("") || pasw.equals("") || u.equals(""))
			return LoginResponse.EMPTY_DATA;
		for(User user : userSet)
			if(user.getUserInfo().getEmail().equals(email) || user.getUserInfo().getUsername().equals(u))
					return LoginResponse.ALREADY_USED;
		
		return LoginResponse.OK;
	}

	public boolean doesRegisterBuyerExist(String dni, int tel) {
		for(User user : userSet) {
			if(user.canBuy()) {
				JSONObject jo = user.getBuyerInfo();
				if (jo!= null){
			        if (jo.get("dni").equals(dni) || jo.getInt("tel") == tel)
			        	return true;
				}
				
			}
		}
			return false;	
	}

	public void addBuyer(String email, String password, String user,String dir, String dni, int tel) {
		UserInfo ui = new UserInfo(new GUID(),email,password,user);
		BuyerInfo bi = new BuyerInfo(dir,dni,tel);
		
		User u = new Buyer(ui,bi);
		if (u!=null)
			userSet.add(u);
	}
	
	public void addSeller(String email, String password, String user,String dir, String dni, int tel, String rfc) {
		UserInfo ui = new UserInfo(new GUID(),email,password,user);
		SellerInfo si = new SellerInfo(dir,dni,tel,rfc);
		
		User u = new Seller(ui,si);
		if (u!=null)
			userSet.add(u);
	}

	public JSONArray getUsuarios() {
		JSONArray  ja = new JSONArray ();
		
		for (User u : userSet) {
			JSONObject tipo = new JSONObject();
			if (u.canBuy()) {
				JSONObject datos = new JSONObject(u.getBuyerInfo().toString());
				tipo.put("data",datos);	
				tipo.put("type","buyer");
			}else {
				JSONObject datos = new JSONObject(u.getSellerInfo().toString());
				tipo.put("data",datos);
				tipo.put("type","seller");
			}
			
			ja.put(tipo);
			
		}
		
		return ja;
	}

}
