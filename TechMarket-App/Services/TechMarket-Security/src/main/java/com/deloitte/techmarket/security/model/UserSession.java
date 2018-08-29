package com.deloitte.techmarket.security.model;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 * UserSession class for JWT token generation.
 *
 */
public class UserSession implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String jwt;
	private String userName;
	private String roles;
	private String uniqeId;
	private Timestamp timestamp;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getUniqeId() {
		return uniqeId;
	}
	public void setUniqeId(String uniqeId) {
		this.uniqeId = uniqeId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "UserSession [userId=" + userId + ", jwt=" + jwt + ", userName=" + userName + ", roles=" + roles
				+ ", uniqeId=" + uniqeId + ", timestamp=" + timestamp + "]";
	}	
}
