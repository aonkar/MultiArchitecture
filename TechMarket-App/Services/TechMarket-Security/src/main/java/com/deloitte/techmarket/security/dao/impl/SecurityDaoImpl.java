package com.deloitte.techmarket.security.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.dto.UserDTO;
import com.deloitte.techmarket.security.dao.ISecurityDao;

/**
 * This DAO class will perform authentication of a user.
 *
 */
@Component
@Repository
public class SecurityDaoImpl implements ISecurityDao {

	public static final String QUERY_SIGNIN = "Select userDto from UserDTO userDto where userDto.userName = :userName and userDto.password = :password";
	public static final String QUERY_FIND_BY_USERNAME = "Select userDto from UserDTO userDto where userDto.userName = :userName";
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Sign-in user Method. Authenticates the user based on the login information
	 * provided.
	 * 
	 * @param UserDTO
	 * @return
	 * @throws ApplicationException
	 */
	public UserDTO signInUser(UserDTO userDto) {

		Query query = this.entityManager.createQuery(QUERY_SIGNIN, UserDTO.class);
		query.setParameter(CommonConstants.COMMON_USERNAME, userDto.getUserName());
		query.setParameter(CommonConstants.COMMON_PASSCODE, userDto.getPassword());
		return (UserDTO) query.getSingleResult();
	}

	/**
	 * Find user Method. This menthod is used to find a user by a username.
	 * 
	 * @param UserDTO
	 * @return
	 * @throws ApplicationException
	 */
	public UserDTO findByUsername(String username) {
		Query query = this.entityManager
				.createQuery(QUERY_FIND_BY_USERNAME, UserDTO.class);
		query.setParameter(CommonConstants.COMMON_USERNAME, username);
		return (UserDTO) query.getSingleResult();
	}
}
