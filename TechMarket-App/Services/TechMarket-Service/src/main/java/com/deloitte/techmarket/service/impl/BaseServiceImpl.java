package com.deloitte.techmarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.exception.ExceptionErrorCodes;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.dao.IBaseDao;
import com.deloitte.techmarket.dto.UserDTO;
import com.deloitte.techmarket.model.ResponseModel;
import com.deloitte.techmarket.rulengine.RuleEngineInterface;
import com.deloitte.techmarket.service.IBaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This service class will perform User Crud operations.
 *
 */

@Service
public class BaseServiceImpl implements IBaseService {

	public static final String LOG_SIGNIN = "Inside SignIn User Service Impl";
	public static final String ROLE = "ROLE_";
	public static final String GET = "get";

	private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

	@Autowired
	public IBaseDao iBaseDao;

	@Value(value = CommonConstants.RULE_ENGINE)
	private String ruleEngineType;
	
	@Autowired
	@Qualifier("drools")
	public RuleEngineInterface ruleEngine;
	
	

	/**
	 * Sign-in Method. Authenticates the user based on the login information
	 * provided.
	 * 
	 * @param UserVO
	 * @param responseJson
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public String signIn(UserVO user, ResponseModel<UserVO> responseJson) throws ApplicationException {
		log.info(LOG_SIGNIN);
		responseJson.setStatus(true);
		String roles = user.getRole().replace(ROLE, "");
		user.setRole(roles);
		user.setPassword(null);
		responseJson.setResponseObject(user);
		responseJson.setMessage(CommonConstants.VALID_USER);
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(responseJson);
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.SIGN_IN_EXCEPTION.getMessage());
		}
	}

	/**
	 * createUser Method. Creates a new user based on the user details provided.
	 * 
	 * @param UserVO
	 * @return
	 * @throws ApplicationException
	 */
	@Transactional
	@Override
	public String createUser(UserVO userVO) throws ApplicationException {
		String response = null;
		try {
			UserDTO userDto = new UserDTO();
			requestMapper(userVO, userDto);
			String category = ruleEngine.getCategory(userVO);
			userDto.setCategory(category);
			ResponseModel<UserVO> responseModel = new ResponseModel<>();
			if (!isExistingUser(userDto)) {
				iBaseDao.createUser(userDto);
				userVO.setUserId(userDto.getUserId());
				updateResponseModel(responseModel, userVO, CommonConstants.RESPONSE_SUCCESS);
			} else {
				userVO = null;
				updateResponseModel(responseModel, userVO, CommonConstants.RESPONSE_EXIST);
			}
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writeValueAsString(responseModel);
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.CREATE_EXCEPTION.getMessage());
		}
		return response;
	}

	/**
	 * Update User Method. Updates the user details with the modified Data.
	 * 
	 * @param UserVO
	 * @return
	 * @throws ApplicationException
	 */
	@Transactional
	@Override
	public String updateUser(UserVO userVO) throws ApplicationException {
		String response = null;
		try {
			UserDTO userDto = new UserDTO();
			requestMapper(userVO, userDto);
			String category = ruleEngine.getCategory(userVO);
			userDto.setCategory(category);
			ResponseModel<UserVO> responseModel = new ResponseModel<>();
			iBaseDao.updateUser(userDto);
			updateResponseModel(responseModel, userVO, CommonConstants.RESPONSE_UPDATE);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writeValueAsString(responseModel);
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.UPDATE_EXCEPTION.getMessage());
		}
		return response;
	}

	/**
	 * Delete User Method. Deletes a user details - Soft delete.
	 * 
	 * @param UserDTO
	 * @return
	 * @throws ApplicationException
	 */
	@Transactional
	@Override
	public String deleteUser(UserVO userVO) throws ApplicationException {
		String response = null;
		try {
			UserDTO userDto = new UserDTO();
			requestMapper(userVO, userDto);
			ResponseModel<UserVO> responseModel = new ResponseModel<>();
			iBaseDao.deleteUser(userDto);
			updateResponseModel(responseModel, userVO, CommonConstants.RESPONSE_DELTE);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writeValueAsString(responseModel);
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.DELETE_EXCEPTION.getMessage());
		}
		return response;
	}

	/**
	 * Get Users Method. Display all the user details to a Admin user.
	 * 
	 * @param UserDTO
	 * @return
	 * @throws ApplicationException
	 */
	@Transactional
	@Override
	public String getUsers() throws ApplicationException {
		String response = null;
		try {
			List<UserDTO> users = iBaseDao.getUsers();
			List<UserVO> listUser = new ArrayList<>();
			responseMapper(listUser, users);
			ResponseModel<List<UserVO>> responseModel = new ResponseModel<>();
			updateResponseModel(responseModel, listUser, GET);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writeValueAsString(responseModel);
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.GET_EXCEPTION.getMessage());
		}
		return response;
	}

	private void updateResponseModel(ResponseModel responseModel, UserVO userVO, String message) {
		responseModel.setMessage(message);
		responseModel.setStatus(true);
		responseModel.setResponseObject(userVO);
	}

	private void updateResponseModel(ResponseModel responseModel, List<UserVO> userVO, String message) {
		responseModel.setMessage(message);
		responseModel.setStatus(true);
		responseModel.setResponseObject(userVO);
	}

	private void requestMapper(UserVO userVO, UserDTO userDTO) {
		userDTO.setUserId(userVO.getUserId());
		userDTO.setUserName(userVO.getUserName());
		if (userVO.getPassword() != null) {
			userDTO.setPassword(userVO.getPassword());
		}
		userDTO.setRole(userVO.getRole());
		userDTO.setAge(userVO.getAge());
		userDTO.setCategory(userVO.getCategory());
		userDTO.setEmail(userVO.getEmail());
		userDTO.setFirstname(userVO.getFirstname());
		userDTO.setLastname(userVO.getLastname());
		userDTO.setMobile(userVO.getMobile());
		if (userVO.getIsUserActive() != null) {
			userDTO.setActive(userVO.getIsUserActive());
		}
	}

	private void responseMapper(UserVO user, UserDTO validUser) {
		user.setRole(validUser.getRole());
		user.setAge(validUser.getAge());
		user.setCategory(validUser.getCategory());
		user.setEmail(validUser.getEmail());
		user.setFirstname(validUser.getFirstname());
		user.setLastname(validUser.getLastname());
		user.setMobile(validUser.getMobile());
		user.setUserId(validUser.getUserId());
		user.setUserName(validUser.getUserName());
	}

	private void responseMapper(List<UserVO> userList, List<UserDTO> validUser) {
		for (UserDTO userDto : validUser) {
			UserVO userVO = new UserVO();
			responseMapper(userVO, userDto);
			userList.add(userVO);
		}
	}

	/**
	 * isExistingUser Method. Display all the user details to a Admin user.
	 * 
	 * @param UserDTO
	 * @return
	 * @throws ApplicationException
	 */
	private boolean isExistingUser(UserDTO userDto) throws ApplicationException {
		String user;
		try {
			user = iBaseDao.findUser(userDto);
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.GET_EXCEPTION.getMessage());
		}
		return user != null ? true : false;
	}

}
