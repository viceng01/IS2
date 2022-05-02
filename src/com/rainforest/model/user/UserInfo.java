package com.rainforest.model.user;

import com.rainforest.core.GUID;

public class UserInfo {
	private GUID userID;
	private String email;
	private String password;
	private String username;
	private String dir;
	private String DNI;
	private int tel;
	
	
	public UserInfo(GUID userID, String email, String password, String username, String dir, String DNI, int telefono) {
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.username = username;
		this.dir = dir;
		this.DNI = DNI;
		this.tel = telefono;
	}
	
	public UserInfo(UserInfo u) {
		this.userID = u.userID;
		this.email = u.email;
		this.password = u.password;
		this.username = u.username;
		// TODO Auto-generated constructor stub
	}

	public UserInfo(String email, String password, String user) {
		this.email = email;
		this.password = password;
		this.username = user;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public GUID getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
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

	public void setUserID(GUID userID) {
		this.userID = userID;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}
	
	
}
