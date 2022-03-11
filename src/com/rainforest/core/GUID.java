package com.rainforest.core;

public class GUID {
	
	private GUID()
	{
		
	}
	
	public static GUID generate()
	{
		return new GUID();
	}

	@Override
	public int hashCode() {
		// TODO: Return unique ID
		return -1;
	}
	
}
