package com.deloitte.techmarket.security;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.security.model.UserSession;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Jwts.class})
public class TokenHandlerTest {

	@InjectMocks
	private TokenHandler tokenHandler = new TokenHandler();

	@Test
	public void parseUserFromToken() {

		String token = "Abcd1234";
		UserSession userSession = Mockito.mock(UserSession.class);
		PowerMockito.mockStatic(Jwts.class);
		JwtParser jwtParser = Mockito.mock(JwtParser.class);
		PowerMockito.when(Jwts.parser()).thenReturn(jwtParser);
		Timestamp timestamp = new Timestamp(123L);
		Mockito.when(userSession.getTimestamp()).thenReturn(timestamp);
		Mockito.when(jwtParser.setSigningKey(timestamp.toString())).thenReturn(jwtParser);
		@SuppressWarnings("unchecked")
		Jws<Claims> jws = Mockito.mock(Jws.class);
		Mockito.when(jwtParser.parseClaimsJws(token)).thenReturn(jws);
		Claims claims = Mockito.mock(Claims.class);
		Mockito.when(jws.getBody()).thenReturn(claims);
		Mockito.when(claims.getSubject()).thenReturn("abc");
		tokenHandler.parseUserFromToken(token, userSession);
	}
	
	@Test
	public void createTokenForUser() {

		String userName ="abc";
		String type = "type";
		Timestamp currentTimestamp = new Timestamp(124L);
		PowerMockito.mockStatic(Jwts.class);
		JwtBuilder jwtParser = Mockito.mock(JwtBuilder.class);
		PowerMockito.when(Jwts.builder()).thenReturn(jwtParser);
		Mockito.when(jwtParser.setSubject(userName)).thenReturn(jwtParser);
		Mockito.when(jwtParser.signWith(SignatureAlgorithm.HS256, currentTimestamp.toString())).thenReturn(jwtParser);
		Mockito.when(jwtParser.claim("type", type)).thenReturn(jwtParser);
		Mockito.when(jwtParser.compact()).thenReturn("abc");
		tokenHandler.createTokenForUser(userName, type, currentTimestamp);
	}
	
	@Test
	public void getAuthentication() {

		UserSession userSession = Mockito.mock(UserSession.class);
		String userName ="abc";
		Mockito.when(userSession.getUserName()).thenReturn(userName);
		Mockito.when(userSession.getRoles()).thenReturn("roles");
		tokenHandler.getAuthentication(userName, userSession);
	}

}
