package com.rainforest.model;

import java.util.HashSet;
import java.util.Set;

import com.rainforest.model.user.User;
import com.rainforest.model.user.registration.UserRegistrationRequest;

/**
 * @author Alex
 *
 */
public class Model {

	private Set<User> userSet;
	
	private Set<UserRegistrationRequest> registrationRequestSet;
	
	public Model() {
		userSet = new HashSet<>();
		
		registrationRequestSet = new HashSet<>();
	}
	
	
	/**
	 * Attempts to add a user to the model. Returns true if the user could be added correctly. Returns false if the user was already in the model,
	 * if the user was null or any other case.
	 * 
	 * @param user : The user to add
	 * @return
	 * 	<b>true</b> if the user was added, <b>false</b> otherwise
	 */
	public boolean addUser(User user) {
		if(user == null) {
			return false;
		}
		return userSet.add(user);
	}
	
	
	/**
	 * Attempts to remove a user from the model. Returns true if the user was previously present in the model and could be removed correctly. Returns
	 * false if the user was not present in the model, if the user to remove was null or any other case.
	 * 
	 * @param user : The user to remove
	 * @return
	 * 	<b>true</b> if the user was removed, <b>false</b> otherwise
	 */
	public boolean removeUser(User user) {
		if(user == null) {
			return false;
		}
		return userSet.remove(user);
	}
	
	public Set<UserRegistrationRequest> getRegistrationRequestsCopy() {
		return new HashSet<>(registrationRequestSet);
	}

	public boolean addUserRegistrationRequest(UserRegistrationRequest urr) {
		if(!isValidRegistrationRequest(urr))
			return false;
		
		return registrationRequestSet.add(urr);
	}
	
	private boolean isValidRegistrationRequest(UserRegistrationRequest rr) {
		// TODO: Validate user registration
		
		return true;
	}
	
	/*
	public void consultaAltasPendientes(Admin admin) {
		for (user user: this.userSet) {
			if ()
			
		}
	}
	
	public void darAltasPendientes(user user, Admin admin) {
		if (user.getuserInfo().getuserID() == null) {
			admin.asignaIDVendedor(user);
		}
		
		
	}
	*/
	
	
}
