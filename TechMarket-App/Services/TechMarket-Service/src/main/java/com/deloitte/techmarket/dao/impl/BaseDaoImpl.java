package com.deloitte.techmarket.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.exception.ExceptionErrorCodes;
import com.deloitte.techmarket.dao.IBaseDao;
import com.deloitte.techmarket.dto.UserDTO;

/**
 * This DAO class will perform User Crud operations.
 *
 */
@Component
@Repository
public class BaseDaoImpl implements IBaseDao {

	public static final String QUERY_DISPLAY_USERS = "Select userDto from UserDTO userDto where userDto.active = :active";
	public static final String QUERY_FIND_USER = "Select userDto from UserDTO userDto where userDto.userName = :userName";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * createUser Method. Creates a new user based on the user details provided.
	 * 
	 * @param UserDTO
	 * @throws ApplicationException
	 */
	@Override
	public void createUser(UserDTO userDto) throws ApplicationException {
		try {
			entityManager.persist(userDto);
			entityManager.flush();
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.CREATE_EXCEPTION.getMessage());
		}
	}

	/**
	 * Update User Method. Updates the user details with the modified Data.
	 * 
	 * @param UserDTO
	 * @throws ApplicationException
	 */
	@Override
	public void updateUser(UserDTO userDto) throws ApplicationException {
		try {
			UserDTO user = getUserById(userDto.getUserId());
			user.setAge(userDto.getAge());
			user.setEmail(userDto.getEmail());
			user.setFirstname(userDto.getFirstname());
			user.setLastname(userDto.getLastname());
			user.setMobile(userDto.getMobile());
			if (userDto.getPassword() != null) {
				user.setPassword(userDto.getPassword());
			}
			entityManager.flush();
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.UPDATE_EXCEPTION.getMessage());
		}
	}

	/**
	 * Delete User Method. Deletes a user details - Soft delete.
	 * 
	 * @param UserDTO
	 * @throws ApplicationException
	 */
	@Override
	public void deleteUser(UserDTO userDto) throws ApplicationException {
		try {
			UserDTO user = getUserById(userDto.getUserId());
			user.setActive(false);
			entityManager.flush();
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.DELETE_EXCEPTION.getMessage());
		}
	}

	/**
	 * Get Users Method. Display all the user details to a Admin user.
	 * 
	 * @param UserDTO
	 * @throws ApplicationException
	 */
	@Override
	public List<UserDTO> getUsers() throws ApplicationException {
		List<UserDTO> list;
		try {
			Query query = this.entityManager.createQuery(QUERY_DISPLAY_USERS, UserDTO.class);
			query.setParameter(CommonConstants.ACTIVE, true);
			list = query.getResultList();
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.GET_EXCEPTION.getMessage());
		}
		return list;
	}

	/**
	 * Get User Method. Display single user info using the userId.
	 * 
	 * @param userId
	 * @throws ApplicationException
	 */

	@Override
	public UserDTO getUserById(int userId) throws ApplicationException {
		return entityManager.find(UserDTO.class, userId);
	}

	/**
	 * Find User Method. Finds a user based on the Username.
	 * 
	 * @param UserDTO
	 * @throws ApplicationException
	 */
	@Override
	public String findUser(UserDTO userDto) throws ApplicationException {
		String result = null;
		try {
			Query query = this.entityManager.createQuery(QUERY_FIND_USER, UserDTO.class);
			query.setParameter(CommonConstants.COMMON_USERNAME, userDto.getUserName());
			result = query.getSingleResult() == null ? null : "";
		} catch (NoResultException e) {
			return null;
		} catch (Exception ex) {
			throw new ApplicationException(ExceptionErrorCodes.GET_EXCEPTION.getMessage());
		}
		return result;
	}

}
