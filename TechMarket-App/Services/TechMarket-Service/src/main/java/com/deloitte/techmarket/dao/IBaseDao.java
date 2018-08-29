package com.deloitte.techmarket.dao;

import java.util.List;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.dto.UserDTO;


public interface IBaseDao {
	

	void createUser(UserDTO userDto) throws ApplicationException;

	void updateUser(UserDTO userDto) throws ApplicationException;

	void deleteUser(UserDTO userDto) throws ApplicationException;

	String findUser(UserDTO userDto) throws ApplicationException;

	UserDTO getUserById(int userId) throws ApplicationException;

	List<UserDTO> getUsers() throws ApplicationException;

}
 