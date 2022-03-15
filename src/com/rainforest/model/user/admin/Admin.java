package com.rainforest.model.user.admin;

import java.util.Set;

import com.rainforest.model.user.seller.Seller;

public class Admin {

	private Set<Seller> sellerSet;
	private int adminID;
	private String adminNombre;
	private static int IDSeller = 1;
	private static int IDBuyer = 1;
	//Por ejemplo: ID: 01, Nombre: Dragos
	
	private Admin(String adminNombre, int adminID) {
		this.adminID = adminID;
		this.adminNombre = adminNombre;
	}
	
	//private Buyer comprador;
	public Admin(Seller vendedor) {
		this.sellerSet.add(vendedor);
		
	}
	
	//leer datos, guardamos y damos o no damos altas 
	private boolean esNombre(String nombre) {
		return nombre.isEmpty();
	}
	
	//Comprobamos que no este vacio el campo y aceptamos
	private boolean esEmail(String email) {
		return email.isEmpty();
	}
	
	
	public boolean esAlta(Seller vendedor) {
		
		return esEmail (vendedor.getSellerInfo().getEmail()) &&
				esNombre(vendedor.getSellerInfo().getName());
	}
	
	public void asignaIDVendedor(Seller vendedor) {
		
			//vendedor.asignaID(IDSeller);
			IDSeller++;
		
		
	}
	
	/*
	public Admin(Buyer comprador) {
		this.comprador = comprador;
		
	}*/

	
	
	
}
