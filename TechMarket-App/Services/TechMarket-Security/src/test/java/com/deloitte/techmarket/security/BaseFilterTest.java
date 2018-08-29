package com.deloitte.techmarket.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.Authentication;

import com.deloitte.techmarket.security.dao.ISecurityDao;
import com.deloitte.techmarket.security.model.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TokenHandler.class, ObjectMapper.class, Authenticate.class })
public class BaseFilterTest {

	@Mock
	private ISecurityDao iSecurityDao;
	@Mock
	private TokenHandler tokenHandler;
	@Mock
	Authenticate authenticate;

	@InjectMocks
	private BaseFilter baseFilter = new BaseFilter();

	@Test
	public void destroy() {
		baseFilter.destroy();
	}

	@Test
	public void doFilterWithRequestAsOPTIONS() throws IOException, ServletException {

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		FilterChain filterChain = Mockito.mock(FilterChain.class);
		PowerMockito.when(request.getMethod()).thenReturn("OPTIONS");
		baseFilter.doFilter(request, response, filterChain);
	}

	@Test
	public void doFilterWithRequestNotAsOPTIONS() throws Exception {

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		FilterChain filterChain = Mockito.mock(FilterChain.class);
		PowerMockito.when(request.getMethod()).thenReturn("GET");
		StringBuffer stringBuffer = new StringBuffer("signIn");
		PowerMockito.when(request.getRequestURL()).thenReturn(stringBuffer);
		ServletInputStream inputStream = Mockito.mock(ServletInputStream.class);
		PowerMockito.when(request.getInputStream()).thenReturn(inputStream);
		Mockito.when(request.getAttribute("status")).thenReturn(Boolean.TRUE);
		baseFilter.doFilter(request, response, filterChain);
	}

	@Test
	public void doFilterWithRequestNotAsOPTIONSWithRequestUrlOtherThanSignIn() throws Exception {

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		FilterChain filterChain = Mockito.mock(FilterChain.class);
		PowerMockito.when(request.getMethod()).thenReturn("GET");
		StringBuffer stringBuffer = new StringBuffer("/mvc/");
		PowerMockito.when(request.getRequestURL()).thenReturn(stringBuffer);
		ServletInputStream inputStream = Mockito.mock(ServletInputStream.class);
		PowerMockito.when(request.getInputStream()).thenReturn(inputStream);
		Mockito.when(request.getAttribute("status")).thenReturn(Boolean.TRUE);
		baseFilter.doFilter(request, response, filterChain);
	}

	@Test
	public void doFilter() throws Exception {

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		FilterChain filterChain = Mockito.mock(FilterChain.class);
		PowerMockito.when(request.getMethod()).thenReturn("GET");
		StringBuffer stringBuffer = new StringBuffer("abc");
		PowerMockito.when(request.getRequestURL()).thenReturn(stringBuffer);
		ServletInputStream inputStream = Mockito.mock(ServletInputStream.class);
		PowerMockito.when(request.getInputStream()).thenReturn(inputStream);
		Mockito.when(request.getAttribute("status")).thenReturn(Boolean.FALSE);
		String authHeader ="HEADER";
		Mockito.when(request.getHeader("Authorization")).thenReturn(authHeader);
		HttpSession httpSession = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession(false)).thenReturn(httpSession);
		UserSession userSession = Mockito.mock(UserSession.class);
		Mockito.when(httpSession.getAttribute("activeUser")).thenReturn(userSession);
		String userName = "abc";
		Mockito.when(tokenHandler.parseUserFromToken(authHeader, userSession)).thenReturn(userName);
		Authentication authentication = Mockito.mock(Authentication.class);
		Mockito.when(tokenHandler.getAuthentication(userName, userSession)).thenReturn(authentication);

		baseFilter.doFilter(request, response, filterChain);
	}
}
