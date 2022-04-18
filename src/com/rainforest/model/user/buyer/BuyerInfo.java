package com.rainforest.model.user.buyer;


public class BuyerInfo{

	private String dir;
	private PaymentMethod metodoPago;
	private String DNI;
	
	public BuyerInfo(String dir, PaymentMethod metodoPago, String DNI) {
		this.dir = dir;
		this.metodoPago= metodoPago;
		this.DNI= DNI;
		
	}


	
}
