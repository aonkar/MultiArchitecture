package com.deloitte.techmarket.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String roles;
	private String password;
	private List<SimpleGrantedAuthority> auths = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		auths.add(new SimpleGrantedAuthority(this.roles));
		return auths;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<SimpleGrantedAuthority> getAuths() {
		return auths;
	}
}
