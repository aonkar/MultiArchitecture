package com.deloitte.techmarket.security;

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
import org.powermock.reflect.Whitebox;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.dto.UserDTO;
import com.deloitte.techmarket.security.dao.ISecurityDao;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TokenHandler.class ,Boolean.class})
public class AuthenticateTest {
	

	@Mock
	private ISecurityDao iSecurityDao;
	
	@Mock
	private TokenHandler tokenHandler;

	@InjectMocks
	private Authenticate authenticate= new Authenticate();
	
	
	@Test (expected = ApplicationException.class)
	public void authenticateUserCustomException() throws ApplicationException{
		UserVO user = new UserVO();
		HttpServletRequest httpRequest = PowerMockito.mock(HttpServletRequest.class);
		HttpServletResponse httpResponse = PowerMockito.mock(HttpServletResponse.class);
		Whitebox.setInternalState(authenticate, "isDBActive",Boolean.TRUE);
		Mockito.doThrow(ApplicationException.class).when(iSecurityDao).signInUser(Mockito.any());
		authenticate.authenticateUser(user, httpRequest, httpResponse);
	}
	
	@Test 
	public void authenticateUser() throws ApplicationException {
		UserVO user = new UserVO();
		user.setUserName("Ankit");
		user.setPassword("abc123");
		HttpServletRequest httpRequest = PowerMockito.mock(HttpServletRequest.class);
		HttpServletResponse httpResponse = PowerMockito.mock(HttpServletResponse.class);
		Whitebox.setInternalState(authenticate, "isDBActive",Boolean.TRUE);
		UserDTO validUser = PowerMockito.mock(UserDTO.class);
		PowerMockito.when(iSecurityDao.signInUser(Mockito.any())).thenReturn(validUser);
		PowerMockito.when(validUser.getUserName()).thenReturn("ankit");
		PowerMockito.when(validUser.getRole()).thenReturn("common_user");
		PowerMockito.when(validUser.getCategory()).thenReturn("Category");
		PowerMockito.when(validUser.getAge()).thenReturn(35);
		PowerMockito.when(validUser.getEmail()).thenReturn("email");
		PowerMockito.when(validUser.getFirstname()).thenReturn("Ankit");
		PowerMockito.when(validUser.getLastname()).thenReturn("lastName");
		PowerMockito.when(validUser.getUserId()).thenReturn(1234);
		HttpSession session = PowerMockito.mock(HttpSession.class);
		PowerMockito.when(httpRequest.getSession()).thenReturn(session);
		authenticate.authenticateUser(user, httpRequest, httpResponse);
	}
	
	@Test 
	public void authenticateUserWithAdminRole() throws ApplicationException {
		UserVO user = new UserVO();
		user.setUserName("Ankit");
		user.setPassword("abc123");
		HttpServletRequest httpRequest = PowerMockito.mock(HttpServletRequest.class);
		HttpServletResponse httpResponse = PowerMockito.mock(HttpServletResponse.class);
		Whitebox.setInternalState(authenticate, "isDBActive",Boolean.TRUE);
		UserDTO validUser = PowerMockito.mock(UserDTO.class);
		PowerMockito.when(iSecurityDao.signInUser(Mockito.any())).thenReturn(validUser);
		PowerMockito.when(validUser.getUserName()).thenReturn("ankit");
		PowerMockito.when(validUser.getRole()).thenReturn("admin");
		PowerMockito.when(validUser.getCategory()).thenReturn("Category");
		PowerMockito.when(validUser.getAge()).thenReturn(35);
		PowerMockito.when(validUser.getEmail()).thenReturn("email");
		PowerMockito.when(validUser.getFirstname()).thenReturn("Ankit");
		PowerMockito.when(validUser.getLastname()).thenReturn("lastName");
		PowerMockito.when(validUser.getUserId()).thenReturn(1234);
		HttpSession session = PowerMockito.mock(HttpSession.class);
		PowerMockito.when(httpRequest.getSession()).thenReturn(session);
		authenticate.authenticateUser(user, httpRequest, httpResponse);
	}
	
	@Test 
	public void authenticateUserWithInvalidUser() throws ApplicationException {
		UserVO user = new UserVO();
		user.setUserName("Ankit");
		user.setPassword("abc123");
		HttpServletRequest httpRequest = PowerMockito.mock(HttpServletRequest.class);
		HttpServletResponse httpResponse = PowerMockito.mock(HttpServletResponse.class);
		Whitebox.setInternalState(authenticate, "isDBActive",Boolean.TRUE);
		PowerMockito.when(iSecurityDao.signInUser(Mockito.any())).thenReturn(null);
		authenticate.authenticateUser(user, httpRequest, httpResponse);
	}
	
}
