package com.deloitte.techmarket.utility;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Logger class for generating the Singleton logger instance.
 *
 */
@Component
@Scope(value = "singleton")
public class LoggerClass {

	static Logger log = Logger.getLogger(LoggerClass.class);

	public Logger getLogger() {
		return log;
	}
}
