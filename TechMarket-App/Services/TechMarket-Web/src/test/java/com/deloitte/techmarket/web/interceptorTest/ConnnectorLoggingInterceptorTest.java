/*package com.deloitte.techmarket.web.interceptorTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import com.deloitte.techmarket.service.IBaseService;
import com.deloitte.techmarket.web.interceptor.ConnnectorLoggingInterceptor;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpMethod.class,HttpStatus.class})
public class ConnnectorLoggingInterceptorTest {
	

	@Mock
	private IBaseService baseService;
	
	@InjectMocks
	private ConnnectorLoggingInterceptor connnectorLoggingInterceptor = new ConnnectorLoggingInterceptor();
	
	
//	@Test 
	public void getRequestDetails() throws Exception {
		
		HttpRequest request = Mockito.mock(HttpRequest.class);
		byte[] body = {'a','b','c','d'};
		HttpHeaders reqHeaxeders = Mockito.mock(HttpHeaders.class);
		Mockito.when(request.getHeaders()).thenReturn(reqHeaxeders);
		Set<Map.Entry<String,List<String>>> entries = new HashSet<>();
		Map.Entry<String,List<String>> map = Mockito.mock(Map.Entry.class);
		entries.add(map);
		Mockito.when(request.getMethod()).thenReturn(Mockito.mock(HttpMethod.class));
		Object[] parameterTypes = {request,body};
		Whitebox.invokeMethod(connnectorLoggingInterceptor, "getRequestDetails", parameterTypes);
		
	}
	
//	@Test 
	public void getResponseDetails() throws Exception {
		
		ClientHttpResponse response = Mockito.mock(ClientHttpResponse.class);
		Mockito.when(response.getStatusCode()).thenReturn(Mockito.mock(HttpStatus.class));
		InputStream inputStream = new FileInputStream("src" +File.separator+ "test" + File.separator + "java" + File.separator + "com"+ 
		File.separator +"deloitte"+ File.separator +"techmarket"+ File.separator +"web"+ File.separator +"interceptor" +File.separator + "ConnnectorLoggingInterceptorTest.java");
		Mockito.when(response.getBody()).thenReturn(inputStream);
		HttpHeaders httpHeaders = Mockito.mock(HttpHeaders.class);
		Mockito.when(response.getHeaders()).thenReturn(httpHeaders);
		Set<Map.Entry<String,List<String>>> entries =  new HashSet<>();
		Map.Entry<String,List<String>> map = Mockito.mock(Map.Entry.class);
		entries.add(map);
		Mockito.when(httpHeaders.entrySet()).thenReturn(entries);
		Object[] parameterTypes = {response};
		Whitebox.invokeMethod(connnectorLoggingInterceptor, "getResponseDetails", parameterTypes);
		
	}
	
	
	
	
	
}
*/