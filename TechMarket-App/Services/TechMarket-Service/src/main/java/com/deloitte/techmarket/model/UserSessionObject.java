package com.deloitte.techmarket.model;
/**
 * 
 * @author aonkar
 *
 */
public class UserSessionObject {

	private String jSessionId;
	private String jwtToken;
	private String userName;
	private String uniqueIdForSession;

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUniqueIdForSession() {
		return uniqueIdForSession;
	}

	public void setUniqueIdForSession(String uniqueIdForSession) {
		this.uniqueIdForSession = uniqueIdForSession;
	}

	@Override
	public String toString() {
		return "UserSessionObject [jSessionId=" + jSessionId + ", jwtToken=" + jwtToken + ", userName=" + userName
				+ ", uniqueIdForSession=" + uniqueIdForSession + "]";
	}
	
	
}
