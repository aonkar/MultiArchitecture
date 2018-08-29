package com.deloitte.techmarket.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.dto.UserDTO;

@RunWith(PowerMockRunner.class)
public class BaseDaoTest {
	
	@InjectMocks
	private BaseDaoImpl baseDao = new BaseDaoImpl();
	@Mock
	private EntityManager entityManager;
	
	@Test
	public void getUsers() throws ApplicationException {
		
		String queryStr = "Select userDto from UserDTO userDto where userDto.active = :active";
		@SuppressWarnings("unchecked")
		TypedQuery<UserDTO> query = Mockito.mock(TypedQuery.class);
		Mockito.when(entityManager.createQuery(queryStr,UserDTO.class)).thenReturn(query);
		@SuppressWarnings("unchecked")
		List<UserDTO> expectedResult = Mockito.mock(List.class);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
		List<UserDTO> resultFRomMethod = baseDao.getUsers();
		Assert.assertEquals(resultFRomMethod, expectedResult);
		
	}
	
	@Test
	public void createUser() throws ApplicationException {
		UserDTO input = Mockito.mock(UserDTO.class);
		baseDao.createUser(input);
	}
	
	@Test
	public void updateUser() throws ApplicationException {
		UserDTO input = Mockito.mock(UserDTO.class);
		int userId = 234;
		Mockito.when(input.getUserId()).thenReturn(userId);
		UserDTO user = Mockito.mock(UserDTO.class);
		Mockito.when(entityManager.find(UserDTO.class, userId)).thenReturn(user);
		Mockito.when(input.getAge()).thenReturn(25);
		Mockito.when(input.getEmail()).thenReturn("abc@abc.com");
		Mockito.when(input.getFirstname()).thenReturn("Gaurav");
		Mockito.when(input.getLastname()).thenReturn("Kamath");
		Mockito.when(input.getMobile()).thenReturn("999999999");
		Mockito.when(input.getPassword()).thenReturn("abc");
		baseDao.updateUser(input);
	}
	
	@Test
	public void deleteUser() throws ApplicationException {
		UserDTO input = Mockito.mock(UserDTO.class);
		int userId = 2;
		Mockito.when(input.getUserId()).thenReturn(userId);
		Mockito.when(entityManager.find(UserDTO.class, userId)).thenReturn(input);
		baseDao.deleteUser(input);
	}
	
	@Test
	public void getUserById() throws ApplicationException {
		UserDTO expectedResult = Mockito.mock(UserDTO.class);
		int userId = 2;
		Mockito.when(entityManager.find(UserDTO.class, userId)).thenReturn(expectedResult);
		UserDTO resultFromMethod = baseDao.getUserById(userId);
		Assert.assertEquals(resultFromMethod, expectedResult);
	}
	
	@Test
	public void isUserPresent() throws ApplicationException {
		UserDTO userDto = Mockito.mock(UserDTO.class);
		@SuppressWarnings("unchecked")
		TypedQuery<UserDTO> query = Mockito.mock(TypedQuery.class);
		Mockito.when(entityManager.createQuery(	"Select userDto from UserDTO userDto where userDto.userName = :userName", UserDTO.class)).thenReturn(query);
		Mockito.when(userDto.getUserName()).thenReturn("gaurav");
		Mockito.when(query.getSingleResult()).thenReturn(userDto);
		baseDao.findUser(userDto);
	}
	
	@Test
	public void isUserAbsent() throws ApplicationException {
		UserDTO userDto = Mockito.mock(UserDTO.class);
		@SuppressWarnings("unchecked")
		TypedQuery<UserDTO> query = Mockito.mock(TypedQuery.class);
		Mockito.when(entityManager.createQuery(	"Select userDto from UserDTO userDto where userDto.userName = :userName", UserDTO.class)).thenReturn(query);
		Mockito.when(userDto.getUserName()).thenReturn("gaurav");
		Mockito.when(query.getSingleResult()).thenReturn(null);
		baseDao.findUser(userDto);
	}


}
