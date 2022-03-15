package com.rainforest.model.user.registration;

public abstract class RegistrationRequest {
	protected abstract String getEmail();

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != getClass())
			return false;

		RegistrationRequest rr = (RegistrationRequest) obj;

		if (rr == this)
			return true;

		return rr.getEmail() == getEmail();
	}
}
