package com.deloitte.techmarket.utility;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * This class has the Configuration information for entity.
 *
 */
@Configuration
@ComponentScan(basePackages={"com.deloitte.techmarket.*"})
@EntityScan(basePackages={"com.deloitte.techmarket.dto"})
public class ConfigurationClass {

}
