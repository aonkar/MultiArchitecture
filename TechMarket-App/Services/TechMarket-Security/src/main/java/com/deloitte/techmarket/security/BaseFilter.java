package com.deloitte.techmarket.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.deloitte.techmarket.common.CommonConstants;
import com.deloitte.techmarket.common.exception.ApplicationException;
import com.deloitte.techmarket.common.model.UserVO;
import com.deloitte.techmarket.security.model.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Order(1)
public class BaseFilter extends GenericFilterBean {

	@Autowired
	private TokenHandler tokenHandler;

	@Autowired
	private Authenticate authenticate;

	@Value(value = CommonConstants.CONTENT_TYPE)
	private String contentType;

	@Value(value = CommonConstants.CHARACTER_ENCODING)
	private String characterEncoding;

	@Value(value = CommonConstants.ACCESS_CONTROL_ALLOW_METHODS)
	private String accessControlAllowMethods;

	@Value(value = CommonConstants.ACCESS_CONTROL_EXPOSE_HEADERS)
	private String accessControlExposeHeaders;

	@Value(value = CommonConstants.ACCESS_CONTROL_ALLOW_HEADERS)
	private String accessControlAllowHeaders;

	@Value(value = CommonConstants.ACESS_CONTROL_MAX_AGE)
	private String accessControlMaxAge;

	@Value(value = CommonConstants.ACCESS_CONTROL_ALLOW_CREDENTIALS)
	private String accessControlAllowCredentials;

	// Non-Compliant
	@Override
	public void destroy() {
	}

	/**
	 * Used to handle CORS problem This filter is called prior all request
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		response.setContentType(contentType);
		httpResponse.setCharacterEncoding(characterEncoding);
		httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader(CommonConstants.ORIGIN));
		httpResponse.setHeader("Access-Control-Allow-Methods", accessControlAllowMethods);
		httpResponse.setHeader("Access-Control-Expose-Headers", accessControlExposeHeaders);
		httpResponse.setHeader("Access-Control-Allow-Headers", accessControlAllowHeaders);
		httpResponse.setHeader("Access-Control-Max-Age", accessControlMaxAge);
		httpResponse.setHeader("Access-Control-Allow-Credentials", accessControlAllowCredentials);
		boolean continueFlow = authentication(httpRequest, httpResponse);
		if (continueFlow) {
			filterChain.doFilter(request, response);
		} else {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

	}

	private boolean authentication(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException {
		boolean authStatus = true;
		if (httpRequest.getMethod().equalsIgnoreCase(CommonConstants.OPTIONS)) {
			httpResponse.setStatus(200);
		} else {
			authStatus = this.processAuthentication(httpRequest, httpResponse);
		}
		return authStatus;
	}
	
	private boolean processAuthentication(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		boolean authStatus = false;
		if (httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_SIGNIN)) {
			authStatus = this.processAuthenticationAnonymousUrls(httpRequest, httpResponse);
		} else if (httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_API)
				|| httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_CONFIGURATION_UI)
				|| httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_SWAGGER)
				|| httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_CONFIGURATION_SECURITY)
				|| httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_SWAGGER_UI)
				|| httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_WEBJARS)
				|| httpRequest.getRequestURL().toString().contains(CommonConstants.BASEFILTER_REQUEST_URL_MVC)) {
			authStatus = true;
		} else {
			authStatus = this.processAuthenticationActiveUserUrls(httpRequest, httpResponse);
		}
		return authStatus;
	}

	private boolean processAuthenticationAnonymousUrls(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		boolean authStatus = true;
		ServletInputStream inputStream = httpRequest.getInputStream();
		
		ObjectMapper mapper = new ObjectMapper();
		if (inputStream.available() > 0) {
			UserVO user = mapper.readValue(inputStream, UserVO.class);
			try {
				authenticate.authenticateUser(user, httpRequest, httpResponse);
			} catch (ApplicationException e) {
				return false;
			}
			boolean status = (boolean) httpRequest.getAttribute(CommonConstants.STATUS);
			if (!status) {
				return false;
			}
		}
		return authStatus;
	}
	
	private boolean processAuthenticationActiveUserUrls(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		boolean authStatus = true;
		final String authHeader = httpRequest.getHeader(CommonConstants.AUTH_HEADER_NAME);
		if (authHeader != null && !authHeader.equals("")) {
			HttpSession httpSession = httpRequest.getSession(false);
			if (httpSession != null) {
				UserSession userSession = (UserSession) httpSession.getAttribute(CommonConstants.ACTIVE_USER);
				String userName = tokenHandler.parseUserFromToken(authHeader, userSession);
				Authentication authentication = tokenHandler.getAuthentication(userName, userSession);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				httpResponse.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
				return false;
			}
		} else {
			return false;
		}
		return authStatus;
	}
}