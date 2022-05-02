package com.rainforest.model.user;

import org.json.JSONObject;

import com.rainforest.model.JSONSerializable;

public abstract class User implements IUserPrivileges, JSONSerializable {

	private UserInfo userInfo;

	public User(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	@Override
	public JSONObject serialize() {
		JSONObject jo = new JSONObject();

		jo.put("user_info", userInfo.serialize());

		return jo;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User))
			return false;

		UserInfo ui = ((User) obj).userInfo;

		if (ui.equals(userInfo))
			return true;

		return super.equals(obj);
	}

}
