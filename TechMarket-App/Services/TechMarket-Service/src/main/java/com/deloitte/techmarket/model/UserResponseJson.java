package com.deloitte.techmarket.model;

/**
 * @author aonkar
 *
 */

public class UserResponseJson {

	private String userName;
	private Integer userId;
	private String jwt;
	private String roles;
	private String displayName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "UserResponseJson [userName=" + userName + ", userId=" + userId + ", jwt=" + jwt + ", roles=" + roles
				+ ", displayName=" + displayName + "]";
	}
	
	
}
