package com.deloitte.techmarket.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;
	
	private UserAuthDetails userAuthDetails;

	public UserAuthentication(UserAuthDetails userAuthDetails) {
		this.userAuthDetails = userAuthDetails;
	}

	@Override
	public String getName() {

		return this.userAuthDetails.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.userAuthDetails.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return this.userAuthDetails.getPassword();
	}

	@Override
	public Object getDetails() {
		return this.userAuthDetails;
	}

	@Override
	public Object getPrincipal() {
		return this.userAuthDetails.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) {
		// Auto-generated method stub

	}

}
