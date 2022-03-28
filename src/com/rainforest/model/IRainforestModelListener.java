package com.rainforest.model;

import com.rainforest.model.user.User;

public interface IRainforestModelListener {
	void onRegister(User currentUser);
}
