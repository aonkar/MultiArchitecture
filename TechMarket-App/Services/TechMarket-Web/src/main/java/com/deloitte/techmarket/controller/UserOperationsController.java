package com.deloitte.techmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.service.IBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * This Controller class will perform User Crud operations.
 *
 */
@RequestMapping(value=CommonConstants.URL_API)
@RestController
@Api(value = CommonConstants.SWAGGER_CONTROLLER_CRUD, tags = CommonConstants.SWAGGER_TAG_CONTROLLER_CRUD)
public class UserOperationsController {

	public static final String SWAGGER_API_CREATE = "Add a new user"; 
	public static final String SWAGGER_API_UPDATE= "Update user details";
	public static final String SWAGGER_API_DELETE = "Delete existing user"; 
	public static final String SWAGGER_API_DISPLAY = "Display all user details"; 
	
	@Autowired
	private IBaseService baseService;

	/**
	 * Create user method.
	 * Creates a new user based on the user details provided.
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 */
	@ApiOperation(value=SWAGGER_API_CREATE)
	@RequestMapping(value = CommonConstants.URL_USER, method = { RequestMethod.POST },produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody UserVO userVO) throws ApplicationException {
		return baseService.createUser(userVO);
	}
	
	/**
	 * Update User method.
	 * Updates the user details with the modified Data.
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 */
	@ApiOperation(value=SWAGGER_API_UPDATE)
	@RequestMapping(value = CommonConstants.URL_USER, method = { RequestMethod.PUT },produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUser(@RequestBody UserVO userVO) throws ApplicationException {
		return baseService.updateUser(userVO);
	}

	/**
	 * Delete User method.
	 * Deletes a user details - Soft delete.
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 */
	@ApiOperation(value=SWAGGER_API_DELETE)
	@RequestMapping(value = CommonConstants.URL_USER, method = { RequestMethod.DELETE },produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUser(@RequestBody UserVO userVO) throws ApplicationException {
		return baseService.deleteUser(userVO);
	}

	/**
	 * Get Users method.
	 * Display all the user details to a admin user.
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 */
	@ApiOperation(value=SWAGGER_API_DISPLAY)
	@RequestMapping(value = CommonConstants.URL_USERS, method = { RequestMethod.GET },produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUsers() throws ApplicationException {
		return baseService.getUsers();
	}

}
