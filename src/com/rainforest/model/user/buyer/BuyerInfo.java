package com.rainforest.model.user.buyer;


public class BuyerInfo{

	private String dir;
	private String DNI;
	private int tel;
	
	public BuyerInfo(String dir, String DNI, int telefono) {
		this.dir = dir;
		this.DNI= DNI;
		this.tel = telefono;
	}

	public String getDir() {
		return dir;
	}

	public String getDNI() {
		return DNI;
	}

	public int getTel() {
		return tel;
	}


	
}
