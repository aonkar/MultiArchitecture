package com.deloitte.techmarket.service;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.model.ResponseModel;

/**
 * @author aonkar
 *
 */
public interface IBaseService {

	String signIn(UserVO user, ResponseModel<UserVO> responseJson) throws ApplicationException;

	String createUser(UserVO userVO) throws ApplicationException;

	String updateUser(UserVO userVO) throws ApplicationException;

	String deleteUser(UserVO userVO) throws ApplicationException;

	String getUsers() throws ApplicationException;
	
}
