package com.rainforest.model.user;

import org.json.JSONObject;

import com.rainforest.core.GUID;
import com.rainforest.model.JSONSerializable;

public class UserInfo implements JSONSerializable {
	private GUID userID;
	private String email;
	private String password;
	private String username;
	private String dir;
	private int tel;

	public UserInfo(GUID userID, String email, String password, String username, String dir, int telefono) {
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.username = username;
		this.dir = dir;
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

	public int getTelephone() {
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

	public void setAddress(String dir) {
		this.dir = dir;
	}

	public void setTelephone(int tel) {
		this.tel = tel;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();

		jo.put("GUID", userID.toString());
		jo.put("email", email);
		jo.put("password", password);
		jo.put("username", username);
		jo.put("dir", dir);
		jo.put("tel", tel);

		return jo;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserInfo))
			return false;

		UserInfo other = (UserInfo) obj;

		if (other.email.equals(email) || other.username.equals(username) || other.userID.equals(userID))
			return true;

		return super.equals(obj);
	}

}
