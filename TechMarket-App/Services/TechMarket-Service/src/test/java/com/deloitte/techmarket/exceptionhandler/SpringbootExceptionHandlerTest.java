package com.deloitte.techmarket.exceptionhandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deloitte.techmarket.common.exception.ApplicationException;

@RunWith(PowerMockRunner.class)
public class SpringbootExceptionHandlerTest {
	
@InjectMocks
private SpringbootExceptionHandler exceptionHandler;

@Test
public void exceptionHandler() {
	
	ApplicationException exception = Mockito.mock(ApplicationException.class);
	exceptionHandler.exceptionHandler(exception);
	
}


}
