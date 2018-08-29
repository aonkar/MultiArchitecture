package com.deloitte.techmarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.dao.IBaseDao;
import com.deloitte.techmarket.drools.DroolsRuleEngine;
import com.deloitte.techmarket.dto.UserDTO;
import com.deloitte.techmarket.model.ResponseModel;

@RunWith(PowerMockRunner.class)
public class BaseServiceImplTest {

	@InjectMocks
	private BaseServiceImpl baseServiceImpl = new BaseServiceImpl();
	
	@Mock
	public IBaseDao iBaseDao;
	
	@Test
	public void signin() throws ApplicationException {
		UserVO userVO = new UserVO();
		ResponseModel<UserVO> responseJson = new ResponseModel<>();
		userVO.setRole("ROLE_Abc");
		responseJson.setStatus(true);
		responseJson.setMessage("valid user");
		responseJson.setResponseObject(userVO);
		baseServiceImpl.signIn(userVO, responseJson);
		
	}
	
	@Test
	public void createUser() throws ApplicationException {
		UserVO userVO = new UserVO();
		userVO.setRole("ROLE_Abc");
		userVO.setAge(23);
		userVO.setFirstname("User1");
		baseServiceImpl.createUser(userVO);
	}
	
	@Test
	public void updateUser() throws ApplicationException {
		UserVO userVO = new UserVO();
		userVO.setUserId(123);
		userVO.setUserName("AbC");
		userVO.setRole("ROLE_Abc");
		userVO.setAge(23);
		userVO.setPassword("test$123");
		userVO.setEmail("abc@test.com");
		userVO.setFirstname("TestAbc");
		userVO.setLastname("TestLastAbc");
		userVO.setMobile("123456");
		userVO.setIsUserActive(true);
		baseServiceImpl.updateUser(userVO);
	}
	
	@Test
	public void deleteUser() throws ApplicationException {
		UserVO userVO = new UserVO();
		userVO.setUserId(123);
		userVO.setUserName("AbC");
		userVO.setRole("ROLE_Abc");
		userVO.setAge(23);
		userVO.setPassword("test$123");
		userVO.setEmail("abc@test.com");
		userVO.setFirstname("TestAbc");
		userVO.setLastname("TestLastAbc");
		userVO.setMobile("123456");
		userVO.setIsUserActive(true);
		baseServiceImpl.deleteUser(userVO);
	}
	
	
	@Test
	public void requestMapper() throws Exception {
		UserVO userVO = new UserVO();
		userVO.setUserId(123);
		userVO.setUserName("AbC");
		userVO.setRole("ROLE_Abc");
		userVO.setAge(23);
		userVO.setPassword("test$123");
		userVO.setEmail("abc@test.com");
		userVO.setFirstname("TestAbc");
		userVO.setLastname("TestLastAbc");
		userVO.setMobile("123456");
		userVO.setIsUserActive(true);
		UserDTO userDTO = new UserDTO();
		Object[] parameterTypes = {userVO,userDTO};
		Whitebox.invokeMethod(baseServiceImpl, "requestMapper", parameterTypes);
	}
	
	@Test
	public void isExistingUserValid() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("TestAbc");
		Mockito.when(iBaseDao.findUser(userDTO)).thenReturn("TestAbc");
		Object[] parameterTypes = {userDTO};
		Whitebox.invokeMethod(baseServiceImpl, "isExistingUser", parameterTypes);
	}
	
	@Test
	public void isExistingUserInValid() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("TestAbc");
		Mockito.when(iBaseDao.findUser(userDTO)).thenReturn(null);
		Object[] parameterTypes = {userDTO};
		Whitebox.invokeMethod(baseServiceImpl, "isExistingUser", parameterTypes);
	}
	
	@Test
	public void updateResponseModel() throws Exception {
		ResponseModel<UserVO> responseModel = new ResponseModel<UserVO>();
		UserVO userVO = new UserVO();
		String string = "Test";
		Object[] parameterTypes = {responseModel,userVO,string};
		Whitebox.invokeMethod(baseServiceImpl, "updateResponseModel", parameterTypes);
		
	}
	
	@Test
	public void getListUsers() throws ApplicationException {
		List<UserDTO> users = new ArrayList<>();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(123);
		userDTO.setUserName("AbC");
		userDTO.setRole("ROLE_Abc");
		userDTO.setAge(23);
		userDTO.setPassword("test$123");
		userDTO.setEmail("abc@test.com");
		userDTO.setFirstname("TestAbc");
		userDTO.setLastname("TestLastAbc");
		userDTO.setMobile("123456");
		userDTO.setActive(true);
		users.add(userDTO);
		Mockito.when(iBaseDao.getUsers()).thenReturn(users);
		//baseServiceImpl.getListUsers();
		baseServiceImpl.getUsers();
		
	}
	
}
