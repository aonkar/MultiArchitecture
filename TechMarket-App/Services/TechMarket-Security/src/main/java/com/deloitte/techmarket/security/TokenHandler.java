package com.deloitte.techmarket.security;

import java.sql.Timestamp;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.security.model.UserSession;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public final class TokenHandler {

	/**
	 * This returns the username from the JWT
	 * 
	 * @param token
	 * @return userName
	 */
	public String parseUserFromToken(String token, UserSession userSession) {
		String username = null;
		username = Jwts.parser().setSigningKey(userSession.getTimestamp().toString()).parseClaimsJws(token).getBody().getSubject();

		return username;
	}

	/**
	 * This creates the JWT using the userName
	 * 
	 * @param userName
	 * @param type
	 * @param currentTimestamp 
	 * @return
	 */
	public String createTokenForUser(String userName, String type, Timestamp currentTimestamp) {
		return Jwts.builder().setSubject(userName).signWith(SignatureAlgorithm.HS256, currentTimestamp.toString()).claim(CommonConstants.TYPE, type)
				.compact();
	}

	/**
	 * Authenticates the valid user
	 * 
	 * @param userName
	 * @param userSession2 
	 * @return
	 */
	public Authentication getAuthentication(String userName, UserSession userSession) {
		UserAuthDetails userdetails = new UserAuthDetails(); 
		if( userSession.getUserName().equalsIgnoreCase(userName)) {
		userdetails.setRoles(userSession.getRoles());
		}
		return new UserAuthentication(userdetails);
	}
}