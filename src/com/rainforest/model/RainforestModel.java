package com.rainforest.model;

import java.util.HashSet;
import java.util.Set;

import com.rainforest.core.GUID;
import com.rainforest.model.user.User;
import com.rainforest.model.user.UserInfo;
import com.rainforest.model.user.admin.Admin;
import com.rainforest.model.user.registration.SellerRegistrationRequest;
import com.rainforest.model.user.registration.UserRegistrationRequest;
import com.rainforest.view.LoginResponse;

/**
 * @author Alex
 *
 */
public class RainforestModel {

	private Set<User> userSet;
	private User currentUser;

	private Set<UserRegistrationRequest> registrationRequestSet;

	public RainforestModel() {
		userSet = new HashSet<>();
		//public UserInfo(GUID userID, String email, String password, String username) {
		UserInfo uInfo = new UserInfo(new GUID(), "d", "1", "dr");
		
		Admin u = new Admin(uInfo);
		
		userSet.add(u);
		registrationRequestSet = new HashSet<>();
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
	
	public void registerBuyer(String email, String password, String username) {
		// TODO: Create and add buyer
	}
	
	public void registerSeller(String email, String password, String username) {
		registrationRequestSet.add(new SellerRegistrationRequest(email, password, username));
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

	public Set<UserRegistrationRequest> getRegistrationRequestsCopy() {
		return new HashSet<>(registrationRequestSet);
	}

	public boolean addUserRegistrationRequest(UserRegistrationRequest urr) {
		if (!isValidRegistrationRequest(urr))
			return false;

		return registrationRequestSet.add(urr);
	}

	private boolean isValidRegistrationRequest(UserRegistrationRequest rr) {
		// TODO: Validate user registration

		return true;
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
		if (u.canDeleteRegistrationRequests())
			return "Admin";
		
		return "";
	}
	
	
	public boolean doesUserExist(String email) {
		for(User user : userSet)
			if(user.getUserInfo().getEmail().equals(email))
					return true;
		
		return false;
	}

	/*
	 * public void consultaAltasPendientes(Admin admin) { for (user user:
	 * this.userSet) { if ()
	 * 
	 * } }
	 * 
	 * public void darAltasPendientes(user user, Admin admin) { if
	 * (user.getuserInfo().getuserID() == null) { admin.asignaIDVendedor(user); }
	 * 
	 * 
	 * }
	 */

}
