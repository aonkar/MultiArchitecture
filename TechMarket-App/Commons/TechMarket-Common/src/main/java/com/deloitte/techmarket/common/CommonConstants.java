package com.deloitte.techmarket.common;

public final class CommonConstants {
	
	private CommonConstants() {
		
	}

	public static final String CONTENT_TYPE = "${contentType}";
	public static final String CHARACTER_ENCODING = "${characterEncoding}";
	public static final String ACCESS_CONTROL_ALLOW_METHODS = "${accessControlAllowMethods}";
	public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "${accessControlExposeHeaders}";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "${accessControlAllowHeaders}";
	public static final String ACESS_CONTROL_MAX_AGE = "${accessControlMaxAge}";
	public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "${accessControlAllowCredentials}";
	public static final String AUTH_HEADER_NAME = "Authorization";
	public static final String ACTIVE_USER = "activeUser";
	public static final String ORIGIN = "Origin";
	public static final String OPTIONS = "OPTIONS";
	public static final String DEFAULT = "default";
	public static final String THREAD = "thread";
	public static final String STATUS = "status";
	public static final String TYPE = "type";
	public static final String ACTIVE = "active";
	public static final String VALID_USER = "valid user";
	public static final String RESPONSE_SUCCESS = "success";
	public static final String RESPONSE_EXIST = "existing";
	public static final String RESPONSE_UPDATE = "updated";
	public static final String RESPONSE_DELTE = "deleted";
	public static final String FILE_ENCODING = "file.encoding";
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String BASEFILTER_REQUEST_URL_SIGNIN = "sign-in";
	public static final String BASEFILTER_REQUEST_URL_API = "/v2/api-docs";
	public static final String BASEFILTER_REQUEST_URL_CONFIGURATION_UI = "/configuration/ui";
	public static final String BASEFILTER_REQUEST_URL_SWAGGER = "/swagger-resources";
	public static final String BASEFILTER_REQUEST_URL_CONFIGURATION_SECURITY = "/configuration/security";
	public static final String BASEFILTER_REQUEST_URL_SWAGGER_UI = "/swagger-ui.html";
	public static final String BASEFILTER_REQUEST_URL_WEBJARS = "/webjars/";
	public static final String BASEFILTER_REQUEST_URL_MVC = "/mvc/";
	public static final String URL_SIGNIN= "/sign-in";
	public static final String URL_USER = "/user";
	public static final String URL_USERS = "/users";
	public static final String URL_API = "/api";
	public static final String URL_LOGOUT = "/logout";
	public static final String DATABASECONFIG_DB_DRIVER = "${db.driver}";
	public static final String DATABASECONFIG_DB_USERNAME = "${db.username}";
	public static final String DATABASECONFIG_DB_PASSCODE = "${db.password}";
	public static final String DATABASECONFIG_DB_URL = "${db.url}";
	public static final String DATABASECONFIG_DB_DATASOURCE = "${db.datasource}";
	public static final String DB_ACTIVE = "${isDBActive}";
	public static final String DATABASECONFIG_HIBERNATE_DIALECT = "${hibernate.dialect}";
	public static final String DATABASECONFIG_HIBERNATE_SQL = "${hibernate.show_sql}";
	public static final String DATABASECONFIG_HIBERNATE_AUTO = "${hibernate.hbm2ddl.auto}";
	public static final String DATABASECONFIG_ENTITY_PKG_SCAN = "${entitymanager.packagesToScan}";
	public static final String COMMON_URL = "url";
	public static final String COMMON_USER = "user";
	public static final String COMMON_USERNAME = "userName";
	public static final String COMMON_PASSCODE = "password";
	public static final String AUTHENTICATION = "Authentication";
	public static final String USERTYPE_COMMON = "common_user";
	public static final String USERTYPE_ADMIN = "admin";
	public static final String SWAGGER_TAG_CONTROLLER_AUTH = "Authentication Controller";
	public static final String SWAGGER_CONTROLLER_CRUD = "User Crud Operations";
	public static final String SWAGGER_TAG_CONTROLLER_CRUD = "User Crud Operations Controller";
	public static final String END_OF_MESSAGE = "]";
	public static final String ODM_GET_CATEGORY_URL = "http://localhost:9090/DecisionService/rest/v1/user_categorization_service/1.0/getUserCategory/1.4/WADL";
	public static final String ODM_DECISION_ID = "category";
	public static final String AGE_STRING = "age";
	public static final String RULE_ENGINE = "ruleEngine";
	
}
