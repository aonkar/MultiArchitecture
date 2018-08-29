package com.deloitte.techmarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.model.ResponseModel;
import com.deloitte.techmarket.service.IBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * This controller class will perform authentication of a user.
 *
 */
@RequestMapping(value=CommonConstants.URL_API)
@RestController
@Api(value = CommonConstants.AUTHENTICATION, tags = CommonConstants.SWAGGER_TAG_CONTROLLER_AUTH)
public class AuthenticationController {
	
	public static final String SWAGGER_API_SIGN = "Authenticates and Logs-in a use"; 
	public static final String SWAGGER_API_LOGOUT= "Logs-out a user";
	public static final String LOGOUT_SUCCESS_MSG = "Logged out successfully";
	
	@Autowired
	private IBaseService baseService;
	
	/**
	 * Sign-in Method. 
	 * Authenticates the user based on the login information provided.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 */
	@ApiOperation(value=SWAGGER_API_SIGN)
	@RequestMapping(value = CommonConstants.URL_SIGNIN, method = { RequestMethod.POST },produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signIn(HttpServletRequest request) throws ApplicationException {
		ResponseModel<UserVO> responseJson = new ResponseModel<>();
		UserVO user = (UserVO) request.getAttribute(CommonConstants.COMMON_USER);
		return baseService.signIn(user,responseJson);
	}
	
	/**
	 * Logout Method. 
	 * Logs out the user and clears all the session details.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 */
	@ApiOperation(value=SWAGGER_API_LOGOUT)
	@RequestMapping(value = CommonConstants.URL_LOGOUT, method = { RequestMethod.GET })
	public ResponseModel<String> logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		ResponseModel<String> model = new ResponseModel<>();
		model.setStatus(true);
		model.setMessage(LOGOUT_SUCCESS_MSG);
		return model;
	}
}
