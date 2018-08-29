package com.deloitte.techmarket.security.dao;

import com.deloitte.techmarket.dto.UserDTO;


/**
 * @author aonkar
 *
 */
public interface ISecurityDao {
	
	UserDTO signInUser(UserDTO userDto);
	
	UserDTO findByUsername(String username);
}
 