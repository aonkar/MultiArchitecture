package com.deloitte.techmarket.hibernate.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.deloitte.techmarket.common.CommonConstants;
import com.zaxxer.hikari.HikariDataSource;

/**
 * This class will perform the Datasource configurations for hibernate
 * 
 */

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	
	public static final String EXCEPTION_MSG = "factory is not a hibernate factory";
	public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String HIBERNATE_SESSION_CONTEXT = "hibernate.current_session_context_class";
	public static final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";

	@Value(CommonConstants.DATABASECONFIG_DB_DRIVER)
	private String dbDriver;

	@Value(CommonConstants.DATABASECONFIG_DB_PASSCODE)
	private String dbPassword;

	@Value(CommonConstants.DATABASECONFIG_DB_URL)
	private String dbURL;

	@Value(CommonConstants.DATABASECONFIG_DB_USERNAME)
	private String dbUsername;
	
	@Value(CommonConstants.DATABASECONFIG_DB_DATASOURCE)
	private String datasource;

	@Value(CommonConstants.DATABASECONFIG_HIBERNATE_DIALECT)
	private String hibernateDialect;

	@Value(CommonConstants.DATABASECONFIG_HIBERNATE_SQL)
	private String hibernateShowSql;

	@Value(CommonConstants.DATABASECONFIG_HIBERNATE_AUTO)
	private String hibernateHBM2ddlAuto;

	@Value(CommonConstants.DATABASECONFIG_ENTITY_PKG_SCAN)
	private String entitymanagerPackagesToScan;
	
	/**
	 * This method will return datasource based on the database connection
	 * configurations provided in the config properties file.
	 * 
	 * @return DataSource
	 */

	@Bean
	public DataSource dataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setMaximumPoolSize(100);
		ds.setDataSourceClassName(datasource);
		ds.addDataSourceProperty(CommonConstants.COMMON_URL, dbURL);
		ds.addDataSourceProperty(CommonConstants.COMMON_USER, dbUsername);
		ds.addDataSourceProperty(CommonConstants.COMMON_PASSCODE, dbPassword);
		return ds;
	}

	@Bean(name = ENTITY_MANAGER_FACTORY)
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(Boolean.getBoolean(hibernateHBM2ddlAuto));
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(this.dataSource());
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan(entitymanagerPackagesToScan);
		emf.setPersistenceUnitName(CommonConstants.DEFAULT);
		emf.setJpaProperties(additionalProperties());
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	

	/**
	 * This method will create the Local Session Factory Bean which will then be
	 * used to access session factory objects
	 * 
	 * @return LocalSessionFactoryBean
	 */
	
	@Bean
	public SessionFactory sessionFactory() {
		if (this.entityManagerFactory().unwrap(SessionFactory.class) == null) {
			throw new NullPointerException(EXCEPTION_MSG);
		}
		return this.entityManagerFactory().unwrap(SessionFactory.class);
	}
	
	
	/**
	 * This method is to set the transaction manager for the hibernate
	 * transaction manager object
	 * 
	 * @return HibernateTransactionManager
	 */
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory());
		return transactionManager;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(HIBERNATE_HBM2DDL_AUTO, hibernateHBM2ddlAuto);
		properties.setProperty(HIBERNATE_DIALECT, hibernateDialect);
		properties.setProperty(HIBERNATE_SESSION_CONTEXT, CommonConstants.THREAD);
		return properties;
	}

}
