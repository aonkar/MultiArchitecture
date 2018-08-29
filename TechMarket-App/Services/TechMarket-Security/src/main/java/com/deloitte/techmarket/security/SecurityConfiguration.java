package com.deloitte.techmarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.deloitte.techmarket.common.CommonConstants;


/**
 * Spring security to create in-memory users with roles
 * 
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BaseFilter baseFilter;

	/**
	 * Authorize the users based on the roles
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().antMatchers(CommonConstants.URL_SIGNIN).permitAll()
				.antMatchers(HttpMethod.GET, CommonConstants.URL_USERS)
				.hasAnyRole(UserRolesEnum.COMMON_USER.toString(), UserRolesEnum.ADMIN.toString())
				.antMatchers(HttpMethod.POST, CommonConstants.URL_USER)
				.hasRole(UserRolesEnum.ADMIN.toString())
				.antMatchers(HttpMethod.PUT, CommonConstants.URL_USER)
				.hasAnyRole(UserRolesEnum.COMMON_USER.toString(), UserRolesEnum.ADMIN.toString())
				.antMatchers(HttpMethod.DELETE, CommonConstants.URL_USER)
				.hasRole(UserRolesEnum.ADMIN.toString());
		
		httpSecurity.addFilterBefore(baseFilter, BasicAuthenticationFilter.class);

	}

}
