package com.deloitte.techmarket.security.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import com.deloitte.techmarket.dto.UserDTO;

@RunWith(PowerMockRunner.class)
public class SecurityDaoImplTest {
	
	@InjectMocks
	private SecurityDaoImpl baseDao = new SecurityDaoImpl();
	@Mock
	private EntityManager entityManager;
	
	@Test
	public void signInUser() {
		
		String queryStr = "Select userDto from UserDTO userDto where userDto.userName = :userName and userDto.password = :password";
		@SuppressWarnings("unchecked")
		TypedQuery<UserDTO> query = Mockito.mock(TypedQuery.class);
		Mockito.when(entityManager.createQuery(queryStr,UserDTO.class)).thenReturn(query);
		UserDTO userDTO = Mockito.mock(UserDTO.class);
		Mockito.when(userDTO.getUserName()).thenReturn("gkamath");
		Mockito.when(userDTO.getPassword()).thenReturn("GPassword");
		baseDao.signInUser(userDTO);
	}
	
	@Test
	public void findByUsername() {
		
		String queryStr = "Select userDto from UserDTO userDto where userDto.userName = :userName";
		@SuppressWarnings("unchecked")
		TypedQuery<UserDTO> query = Mockito.mock(TypedQuery.class);
		Mockito.when(entityManager.createQuery(queryStr,UserDTO.class)).thenReturn(query);
		UserDTO expectedResult = Mockito.mock(UserDTO.class);
		Mockito.when(query.getSingleResult()).thenReturn(expectedResult);
		//Method call of UserDao
		UserDTO resultFRomMethod = baseDao.findByUsername("abc");
		Assert.assertEquals(resultFRomMethod, expectedResult);
	}
}
