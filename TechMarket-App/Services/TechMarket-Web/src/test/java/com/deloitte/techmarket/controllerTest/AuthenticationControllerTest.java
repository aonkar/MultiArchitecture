package com.deloitte.techmarket.controllerTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.controller.AuthenticationController;
import com.deloitte.techmarket.service.IBaseService;

@RunWith(PowerMockRunner.class)
public class AuthenticationControllerTest {
	

	@Mock
	private IBaseService baseService;
	
	@InjectMocks
	private AuthenticationController authenticationController = new AuthenticationController();
	
	
	@Test 
	public void signIn() throws ApplicationException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		UserVO user = Mockito.mock(UserVO.class);
		Mockito.when(request.getAttribute("user")).thenReturn(user);
		authenticationController.signIn(request);
	}
	
	@Test 
	public void logout() throws ApplicationException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		Mockito.when(request.getSession(false)).thenReturn(session);
		authenticationController.logout(request, response);
	}
	
	
	
}
