package com.deloitte.techmarket.hibernate.config;

import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
public class DatabaseConfigTest {

	@InjectMocks
	private DatabaseConfig databaseConfig = new DatabaseConfig();
	
	@Test
	public void dataSource() {
		Whitebox.setInternalState(databaseConfig, "dbURL","http://laecl:8989/");
		Whitebox.setInternalState(databaseConfig, "dbUsername","usename");
		Whitebox.setInternalState(databaseConfig, "dbPassword","password");
		databaseConfig.dataSource();
	}
	
	//It will throw exception as we are providing wrong url details
	@Test(expected = ServiceException.class)
	public void entityManagerFactory() {
		Whitebox.setInternalState(databaseConfig, "dbURL","http://laecl:8989/");
		Whitebox.setInternalState(databaseConfig, "dbUsername","usename");
		Whitebox.setInternalState(databaseConfig, "dbPassword","password");
		Whitebox.setInternalState(databaseConfig, "hibernateHBM2ddlAuto", Boolean.FALSE.toString());
		Whitebox.setInternalState(databaseConfig, "entitymanagerPackagesToScan", "com.deloitte");
		Whitebox.setInternalState(databaseConfig, "hibernateDialect", "mysql");
		databaseConfig.entityManagerFactory();
		
	}
}
