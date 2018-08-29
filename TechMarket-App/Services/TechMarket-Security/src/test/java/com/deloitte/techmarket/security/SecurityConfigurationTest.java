package com.deloitte.techmarket.security;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpSecurity.class,CsrfConfigurer.class,FormLoginConfigurer.class,LogoutConfigurer.class})
public class SecurityConfigurationTest {

	@InjectMocks
	private SecurityConfiguration securityConfiguration = new SecurityConfiguration();
	@Mock
	BaseFilter baseFilter;

//	@Test
//	public void configure() throws Exception {
//		AuthenticationManagerBuilder auth = PowerMockito.mock(AuthenticationManagerBuilder.class);
//		securityConfiguration.configure(auth);
//	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void configureWithHttpSecurity() throws Exception {
		HttpSecurity httpSecurity = PowerMockito.mock(HttpSecurity.class);
		CsrfConfigurer<HttpSecurity> configurer = PowerMockito.mock(CsrfConfigurer.class);
		PowerMockito.when(httpSecurity.csrf()).thenReturn(configurer);
		@SuppressWarnings("rawtypes")
		ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = PowerMockito.mock(ExpressionInterceptUrlRegistry.class);
		PowerMockito.when(httpSecurity.authorizeRequests()).thenReturn(expressionInterceptUrlRegistry);
		@SuppressWarnings("rawtypes")
		AuthorizedUrl authorizedUrl = PowerMockito.mock(AuthorizedUrl.class);
		PowerMockito.when(expressionInterceptUrlRegistry.antMatchers(Mockito.any(HttpMethod.class),Mockito.anyString())).thenReturn(authorizedUrl);
		PowerMockito.when(expressionInterceptUrlRegistry.antMatchers(Mockito.anyString())).thenReturn(authorizedUrl);
		PowerMockito.when(authorizedUrl.permitAll()).thenReturn(expressionInterceptUrlRegistry);
		PowerMockito.when(authorizedUrl.hasAnyRole(Mockito.anyString(),Mockito.anyString())).thenReturn(expressionInterceptUrlRegistry);
		PowerMockito.when(authorizedUrl.hasRole(Mockito.anyString())).thenReturn(expressionInterceptUrlRegistry);
		PowerMockito.when(authorizedUrl.authenticated()).thenReturn(expressionInterceptUrlRegistry);
		PowerMockito.when(expressionInterceptUrlRegistry.and()).thenReturn(httpSecurity);
		@SuppressWarnings("rawtypes")
		FormLoginConfigurer formLoginConfigurer = PowerMockito.mock(FormLoginConfigurer.class);
		PowerMockito.when(httpSecurity.formLogin()).thenReturn(formLoginConfigurer);
		PowerMockito.when(formLoginConfigurer.loginPage(Mockito.anyString())).thenReturn(formLoginConfigurer);
		PowerMockito.when(formLoginConfigurer.defaultSuccessUrl(Mockito.anyString())).thenReturn(formLoginConfigurer);
		PowerMockito.when(formLoginConfigurer.permitAll()).thenReturn(formLoginConfigurer);
		PowerMockito.when(formLoginConfigurer.and()).thenReturn(httpSecurity);
		@SuppressWarnings({"rawtypes" })
		LogoutConfigurer logoutConfigurer = PowerMockito.mock(LogoutConfigurer.class);
		PowerMockito.when(httpSecurity.logout()).thenReturn(logoutConfigurer);
		PowerMockito.when(logoutConfigurer.permitAll()).thenReturn(logoutConfigurer);
		securityConfiguration.configure(httpSecurity);
		
		
	}
	
}
