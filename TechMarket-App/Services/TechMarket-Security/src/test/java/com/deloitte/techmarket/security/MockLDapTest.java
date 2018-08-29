package com.deloitte.techmarket.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.common.model.UserVO;

@RunWith(PowerMockRunner.class)
public class MockLDapTest {


	@InjectMocks
	private MockLdap mockLdap = new MockLdap();

	@Test
	public void authenticate() {
		UserVO user = new UserVO();
		user.setUserName("admin");
		mockLdap.authenticate(user);
		user.setUserName("common-user");
		mockLdap.authenticate(user);
		user.setUserName("abc");
		mockLdap.authenticate(user);
	}
	
}
