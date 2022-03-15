package com.rainforest.model;

import java.util.HashSet;
import java.util.Set;

import com.rainforest.model.admin.Admin;
import com.rainforest.model.registration.RegistrationRequest;
import com.rainforest.model.registration.SellerRegistrationRequest;
import com.rainforest.model.seller.Seller;

/**
 * @author Alex
 *
 */
public class Model {

	private Set<Seller> sellerSet;
	private Set<Admin> adminSet;
	
	private Set<RegistrationRequest> registrationRequestSet;
	
	public Model() {
		sellerSet = new HashSet<>();
		adminSet = new HashSet<>();
		
		registrationRequestSet = new HashSet<>();
	}
	
	
	/**
	 * Attempts to add a seller to the model. Returns true if the seller could be added correctly. Returns false if the seller was already in the model,
	 * if the seller was null or any other case.
	 * 
	 * @param seller : The seller to add
	 * @return
	 * 	<b>true</b> if the seller was added, <b>false</b> otherwise
	 */
	public boolean addSeller(Seller seller) {
		if(seller == null) {
			return false;
		}
		return sellerSet.add(seller);
	}
	
	
	/**
	 * Attempts to remove a seller from the model. Returns true if the seller was previously present in the model and could be removed correctly. Returns
	 * false if the seller was not present in the model, if the seller to remove was null or any other case.
	 * 
	 * @param seller : The seller to remove
	 * @return
	 * 	<b>true</b> if the seller was removed, <b>false</b> otherwise
	 */
	public boolean removeSeller(Seller seller) {
		if(seller == null) {
			return false;
		}
		return sellerSet.remove(seller);
	}
	
	public boolean addAdmin (Admin admin) {
		if (admin == null) {
			return false;
		}
		return adminSet.add(admin);
	}
	
	public boolean removeAdmin (Admin admin) {
		if (admin == null) {
			return false;
		}
		return adminSet.remove(admin);
	}
	
	public Set<RegistrationRequest> getRegistrationRequestsCopy() {
		return new HashSet<>(registrationRequestSet);
	}


	public boolean createSellerRegistrationRequest(String name, String email) {
		SellerRegistrationRequest srr =  new SellerRegistrationRequest(name, email);

		if(!isValidRegistrationRequest(srr))
			return false;
		
		registrationRequestSet.add(new SellerRegistrationRequest(name, email));
		
		return true;
	}
	
	private boolean isValidRegistrationRequest(RegistrationRequest rr) {
		for (RegistrationRequest regReq : registrationRequestSet)
			if(regReq.equals(rr))
				return false;
		
		return true;
	}
	
	/*
	public void consultaAltasPendientes(Admin admin) {
		for (Seller seller: this.sellerSet) {
			if ()
			
		}
	}
	
	public void darAltasPendientes(Seller seller, Admin admin) {
		if (seller.getSellerInfo().getSellerID() == null) {
			admin.asignaIDVendedor(seller);
		}
		
		
	}
	*/
	
	
}
