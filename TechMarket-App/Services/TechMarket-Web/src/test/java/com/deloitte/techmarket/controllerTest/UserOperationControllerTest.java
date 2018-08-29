package com.deloitte.techmarket.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.controller.UserOperationsController;
import com.deloitte.techmarket.service.IBaseService;

@RunWith(PowerMockRunner.class)
public class UserOperationControllerTest {

	@Mock
	private IBaseService baseService;
	@Mock
	private UserVO userVO;

	@InjectMocks
	private UserOperationsController userOperationsController = new UserOperationsController();

	@Test
	public void createUpdateDeleteAndGetUser() throws ApplicationException {

		userOperationsController.createUser(userVO);
		userOperationsController.deleteUser(userVO);
		userOperationsController.updateUser(userVO);
		userOperationsController.getUsers();
	}

}
