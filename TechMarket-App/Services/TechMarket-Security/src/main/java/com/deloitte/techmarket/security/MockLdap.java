package com.deloitte.techmarket.security;

import org.springframework.stereotype.Component;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.model.UserVO;

@Component
public class MockLdap {

	public boolean authenticate(UserVO user) {
		boolean apiCallToAuthenticate = true; // THis will be a Rest call to Deloitte Active directory to
		// Authenticate an user if ldap access is not available to a person i.e. for
		// testing team who donot have access to the Innowake network
		String role = null;
		if (user.getUserName().equalsIgnoreCase(CommonConstants.USERTYPE_ADMIN)) {
			role = UserRolesEnum.ROLE_ADMIN.toString();
		} else if (user.getUserName().equalsIgnoreCase(CommonConstants.USERTYPE_COMMON)) {
			role = UserRolesEnum.ROLE_COMMON_USER.toString();
		} else {
			apiCallToAuthenticate = false;
		}
		user.setRole(role);
		return apiCallToAuthenticate;
	}

}
