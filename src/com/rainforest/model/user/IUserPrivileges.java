package com.rainforest.model.user;

import com.rainforest.core.GUID;

public interface IUserPrivileges {
	public boolean canSell();
	public boolean canBuy();
	public void removeProduct(GUID name);
	
}
