package com.deloitte.techmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.deloitte.techmarket.common.CommonConstants;

/**
 * 
 * TechMarket Main class file.
 * Application's entry point
 */
//@EnableDiscoveryClient
@SpringBootApplication
public class TechMarketMain extends SpringBootServletInitializer{

	public static void main(String[] args) {
		System.getProperty(CommonConstants.FILE_ENCODING,CommonConstants.ENCODING_UTF8);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setActiveProfiles("odm");
		ctx.close();
		SpringApplication.run(TechMarketMain.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<TechMarketMain> applicationClass = TechMarketMain.class;
}