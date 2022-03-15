package com.rainforest.model.user.registration;

public abstract class UserRegistrationRequest {
	protected abstract String getEmail();

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != getClass())
			return false;

		UserRegistrationRequest rr = (UserRegistrationRequest) obj;

		if (rr == this)
			return true;

		return rr.getEmail() == getEmail();
	}
}
