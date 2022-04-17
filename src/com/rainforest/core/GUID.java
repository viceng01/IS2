package com.rainforest.core;

import java.util.UUID;


/**
 * @author Alex
 * Wrapper class for Unique Universal IDentifier. Acronym stands for Global Unique IDentifier. Offers additional functionality specific to this project.
 */
public class GUID {
	
	private UUID uuid;
	
	public GUID(){
		//A-GUID
		//B-GUID
		//S-GUID
		uuid = UUID.randomUUID();
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public String toString() {
		return uuid.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(obj.getClass().equals(getClass()))
			return false;
		
		return uuid.equals(((GUID)obj).uuid);
	}
}
