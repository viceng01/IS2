package com.rainforest.model;

import java.util.HashSet;
import java.util.Set;

import com.rainforest.model.admin.Admin;
import com.rainforest.model.seller.Seller;

public class Model {

	private Set<Seller> sellerSet;
	private Set<Admin> adminSet;
	
	
	public Model() {
		sellerSet = new HashSet<>();
		adminSet = new HashSet<>();
	}
	
	public boolean addSeller(Seller seller) {
		if(seller == null) {
			return false;
		}
		return sellerSet.add(seller);
	}
	
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
