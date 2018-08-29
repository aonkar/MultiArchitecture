package com.deloitte.techmarket.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.security.model.UserSession;
import com.deloitte.techmarket.utility.LoggerClass;

@RunWith(PowerMockRunner.class)
public class LoggingClassTest {

	@InjectMocks
	private LoggingClass loggingClass = new LoggingClass();
	@Mock
	private LoggerClass log;
	@Mock
	private HttpServletRequest request;
	
	@Test
	public void testLogClass() {
		Logger logger = Mockito.mock(Logger.class);
		Mockito.when(log.getLogger()).thenReturn(logger);
		JoinPoint joinPoint = Mockito.mock(JoinPoint.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		Mockito.when( request.getSession(false)).thenReturn(session);
		UserSession userSession = Mockito.mock(UserSession.class);
		Mockito.when(session.getAttribute("activeUser")).thenReturn(userSession);
		Mockito.when(userSession.getUserName()).thenReturn("gaurav");
		Signature signature = Mockito.mock(Signature.class);
		Mockito.when(joinPoint.getSignature()).thenReturn(signature);
		Mockito.when(signature.getName()).thenReturn("gaurav");
		Object[] args = {"a","b","c"};
		Mockito.when(joinPoint.getArgs()).thenReturn(args);
		loggingClass.logBeforeController(joinPoint);
		loggingClass.logBeforeAuthenticate(joinPoint);
		loggingClass.logAfterAuthenticate(joinPoint);
		loggingClass.logAfterController(joinPoint);
		loggingClass.logBeforeIBaseDao(joinPoint);
		loggingClass.logAfterIBaseDao(joinPoint);
		loggingClass.logAfterService(joinPoint);
		loggingClass.logBeforeService(joinPoint);
		loggingClass.logExceptionForIBaseService(joinPoint, new Exception());
		loggingClass.logExceptionForAuthenticate(joinPoint, new Exception());
	}
}
