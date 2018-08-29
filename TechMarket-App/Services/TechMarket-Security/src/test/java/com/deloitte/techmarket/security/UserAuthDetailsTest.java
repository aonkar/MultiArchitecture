package com.deloitte.techmarket.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class UserAuthDetailsTest {

	@InjectMocks
	private UserAuthDetails userAuth = new UserAuthDetails();
	@InjectMocks
	private UserAuthentication userAuthentication = new UserAuthentication(userAuth);
	
	
	@Test(expected=IllegalArgumentException.class)
	public void grantedAuthorities() {
		userAuth.getAuthorities();
		
	}
	
	@Test()
	public void grantedAuthoritiesWithRoles() {
		userAuth.setRoles("roles");
		userAuth.getAuthorities();
		
	}

	@Test
	public void userAuth() {
		userAuth.getAuths();
		userAuth.getPassword();
		userAuth.getRoles();
		userAuth.getUsername();
		userAuth.isAccountNonExpired();
		userAuth.isAccountNonLocked();
		userAuth.isCredentialsNonExpired();
		userAuth.isEnabled();
		userAuth.setRoles("roles");
		userAuthentication.getAuthorities();
		userAuthentication.getCredentials();
		userAuthentication.getDetails();
		userAuthentication.getName();
		userAuthentication.getPrincipal();
		userAuthentication.setAuthenticated(true);
		userAuthentication.isAuthenticated();
	}
	
}
