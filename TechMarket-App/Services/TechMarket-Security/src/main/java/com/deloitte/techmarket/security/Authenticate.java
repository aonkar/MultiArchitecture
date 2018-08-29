package com.deloitte.techmarket.security;

import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.exception.ExceptionErrorCodes;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.dto.UserDTO;
import com.deloitte.techmarket.security.dao.ISecurityDao;
import com.deloitte.techmarket.security.model.UserSession;


@Component
public class Authenticate {

	@Autowired
	private TokenHandler tokenHandler;

	@Autowired
	private ISecurityDao iSecurityDao;

	@Value(value = CommonConstants.DB_ACTIVE)
	private Boolean isDBActive;

	public void authenticateUser(UserVO user, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws ApplicationException {

		if (isDBActive) {
			try {
				authenticateUsingDB(user, httpRequest, httpResponse);
			} catch (Exception e) {
				throw new ApplicationException(ExceptionErrorCodes.SIGN_IN_EXCEPTION.getMessage());
			}
		}
	}

	private void authenticateUsingDB(UserVO user, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws ApplicationException {

		try {
			UserDTO userDto = new UserDTO();
			userDto.setUserName(user.getUserName());
			userDto.setPassword(user.getPassword());
			UserDTO validUser = iSecurityDao.signInUser(userDto);
			if (validUser != null) {
				UserSession userSession = new UserSession();
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				userSession.setTimestamp(currentTimestamp);
				String jwt = generateJwt(validUser.getUserName(), currentTimestamp);
				responseMapper(user, validUser, jwt);
				httpResponse.setHeader(CommonConstants.AUTHENTICATION, jwt);
				HttpSession session = httpRequest.getSession();
				UUID uuid = UUID.randomUUID();
				userSession.setJwt(jwt);
				if(validUser.getRole() != null && validUser.getRole().equalsIgnoreCase(CommonConstants.USERTYPE_COMMON)) {
					userSession.setRoles(UserRolesEnum.ROLE_COMMON_USER.toString());
				}else if(validUser.getRole() != null && validUser.getRole().equalsIgnoreCase(CommonConstants.USERTYPE_ADMIN)) {
					userSession.setRoles(UserRolesEnum.ROLE_ADMIN.toString());
				}
				userSession.setUserName(validUser.getUserName());
				userSession.setUniqeId(uuid.toString());
				httpRequest.setAttribute(CommonConstants.STATUS, true);
				httpRequest.setAttribute(CommonConstants.COMMON_USER, user);
				session.setAttribute(CommonConstants.ACTIVE_USER, userSession);
				
			} else {
				httpRequest.setAttribute("status", false);
			}
		} catch (Exception e) {
			throw new ApplicationException(ExceptionErrorCodes.SIGN_IN_EXCEPTION.getMessage());
		}

	}

	private void responseMapper(UserVO user, UserDTO validUser, String jwt) {
		user.setRole(validUser.getRole());
		user.setJwt(jwt);
		user.setAge(validUser.getAge());
		user.setCategory(validUser.getCategory());
		user.setEmail(validUser.getEmail());
		user.setFirstname(validUser.getFirstname());
		user.setLastname(validUser.getLastname());
		user.setMobile(validUser.getMobile());
		user.setUserId(validUser.getUserId());
	}

	/**
	 * @param user
	 * @param userName
	 * @param currentTimestamp
	 * @param responseModel
	 * @return
	 */
	private String generateJwt(String userName, Timestamp currentTimestamp) {
		String type = "loginToken";
		return tokenHandler.createTokenForUser(userName, type, currentTimestamp);
	}
}
