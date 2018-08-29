package com.deloitte.techmarket.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.dto.UserDTO;
import com.deloitte.techmarket.security.dao.ISecurityDao;

@RunWith(PowerMockRunner.class)
public class UserDetailsProviderTest {

	@InjectMocks
	private UserDetailsProvider userDetailsProvider = new UserDetailsProvider();
	@Mock
	private ISecurityDao iBaseDao;
	
	@Test
	public void getUserDetails() {
		UserDTO user = new UserDTO();
		user.setRole("role");
		user.setUserName("abc");
		user.setPassword("@#$as3");
		Mockito.when(iBaseDao.findByUsername("abcd")).thenReturn(user);
		userDetailsProvider.getUserDetails("abcd");
	}
	
	
}
