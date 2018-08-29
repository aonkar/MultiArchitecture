package com.deloitte.techmarket.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.deloitte.techmarket.dto.UserDTO;
import com.deloitte.techmarket.security.dao.ISecurityDao;

@Component
public class UserDetailsProvider {

	@Autowired
	private ISecurityDao iSecurityDao;
	
	public UserDetails getUserDetails(String username){
		UserDTO user = iSecurityDao.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		String role = user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        
        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}
}
