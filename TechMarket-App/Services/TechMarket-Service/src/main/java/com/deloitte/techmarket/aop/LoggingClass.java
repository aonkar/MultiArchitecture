package com.deloitte.techmarket.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.techmarket.security.model.UserSession;
import com.deloitte.techmarket.utility.LoggerClass;

/**
 * This class will perform the logging of the Tech Market Application.
 *
 */
@Aspect
@Component
public class LoggingClass {
	
	public static final String LOGGEDUSER = "Logged in user : ";

	@Autowired
	private LoggerClass log;

	@Autowired
	private HttpServletRequest request;

	/**
	 * Logs the entry of the flow into controller methods in the log files
	 * 
	 * @param joinPoint
	 * 
	 */
	@Before("execution(* com.deloitte.techmarket.controller.AuthenticationController.*(..))")
	public void logBeforeController(JoinPoint joinPoint) {
		if (getLoggedInUser(request) != null) {
			log.getLogger().error(LOGGEDUSER + getLoggedInUser(request));
		}
		log.getLogger().info("Entering Controller method: " + joinPoint.getSignature().getName());
		if (joinPoint.getArgs().length > 0) {
			log.getLogger().info("Json Request Data from the UI" + joinPoint.getArgs()[0]);
		}
	}

	/**
	 * Logs the exit of the flow out of controller methods in the log files
	 * 
	 * @param joinPoint
	 */
	@After("execution(* com.deloitte.techmarket.controller.AuthenticationController.*(..))")
	public void logAfterController(JoinPoint joinPoint) {
		if (getLoggedInUser(request) != null) {
			log.getLogger().error(LOGGEDUSER + getLoggedInUser(request));
		}
		log.getLogger().info("Exiting Controller method : " + joinPoint.getSignature().getName());
	}

	/**
	 * Logs the entry of the flow into service methods in the log files
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* com.deloitte.techmarket.service.IBaseService.*(..))")
	public void logBeforeService(JoinPoint joinPoint) {
		if (getLoggedInUser(request) != null) {
			log.getLogger().error(LOGGEDUSER + getLoggedInUser(request));
		}
		log.getLogger().info("Entering Service method: " + joinPoint.getSignature().getName());
		if (joinPoint.getArgs().length > 0) {
			log.getLogger().info("Json Request Data from the UI" + joinPoint.getArgs()[0]);
		}
	}

	/**
	 * Logs the exit of the flow out of service methods in the log files
	 * 
	 * @param joinPoint
	 */
	@After("execution(* com.deloitte.techmarket.service.IBaseService.*(..))")
	public void logAfterService(JoinPoint joinPoint) {
		if (getLoggedInUser(request) != null) {
			log.getLogger().error(LOGGEDUSER + getLoggedInUser(request));
		}
		log.getLogger().info("Exiting Service method: " + joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "execution(* com.deloitte.techmarket.service.IBaseService.*(..))", throwing = "ex")
	public void logExceptionForIBaseService(JoinPoint joinPoint, Exception ex) {
		if (getLoggedInUser(request) != null) {
			log.getLogger().error(LOGGEDUSER + getLoggedInUser(request));
		}
		log.getLogger().info("Exception Service method : " + joinPoint.getSignature().getName() + "\n", ex);
	}

	@Before("execution(* com.deloitte.techmarket.security.Authenticate.*(..)) && !execution(* com.deloitte.techmarket.security.Authenticate.generateJwt*(..))")
	public void logBeforeAuthenticate(JoinPoint joinPoint) {
		log.getLogger().info("Entering Authenticate method: " + joinPoint.getSignature().getName());
		log.getLogger().info("Logged in user details" + joinPoint.getArgs()[0]);
	}

	@Before("execution(* com.deloitte.techmarket.dao.IBaseDao.*(..))")
	public void logBeforeIBaseDao(JoinPoint joinPoint) {
		log.getLogger().info("Entering Dao method: " + joinPoint.getSignature().getName());
	}

	/**
	 * Logs the exit of the flow out of Dao methods in the log files
	 * 
	 * @param joinPoint
	 */
	@After("execution(* com.deloitte.techmarket.dao.IBaseDao.*(..))")
	public void logAfterIBaseDao(JoinPoint joinPoint) {
		log.getLogger().info("Exiting Dao method: " + joinPoint.getSignature().getName());
	}

	/**
	 * Logs the exit of the flow out of Security methods in the log files
	 * 
	 * @param joinPoint
	 */
	@After("execution(* com.deloitte.techmarket.security.Authenticate.*(..))")
	public void logAfterAuthenticate(JoinPoint joinPoint) {
		log.getLogger().info("Exiting Authenticate method: " + joinPoint.getSignature().getName());
		log.getLogger().info("Logged in user after populating data from the ldap" + joinPoint.getArgs()[0]);
	}

	@AfterThrowing(pointcut = "execution(* com.deloitte.techmarket.security.Authenticate.*(..))", throwing = "ex")
	public void logExceptionForAuthenticate(JoinPoint joinPoint, Exception ex) {
		if (getLoggedInUser(request) != null) {
			log.getLogger().error(LOGGEDUSER + getLoggedInUser(request));
		}
		log.getLogger().error("Exception Security method : " + joinPoint.getSignature().getName() + "\n", ex);
	}

	private String getLoggedInUser(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			UserSession userSession = (UserSession) session.getAttribute("activeUser");
			return userSession.getUserName();
		}
		return null;
	}
}
