module.exports = {
	APP_NAME: 'React Sample App',
	APP_TITLE: 'React Sample App USI-SI',
	VERSION: 'v1-0-0',

	SOURCE_FOLDER: 'src',
	ENTRY_POINT: 'app/index.js',

	WEB_ROOT: 'www-root', // Serve this folder in webpack-dev-server [Similar to DIST folder]

	/*
	*    Never END with '/'
	*    /site-name  => If site is hosted on Shared Machine
	*    /           => If site is hosted on Dedicated Machine
	*/
	REACT_PUBLIC_PATH: 'react-sample-app',

	JS_FOLDER: 'js',
	CSS_FOLDER: 'css',
	ASSETS_FOLDER: 'assets',

	DEV_SERVER_HOST: 'localhost', // Use 0.0.0.0 if wanted to access it over LAN using Machine IP address
	DEV_SERVER_PORT: 6565,

	API_BASE_URL: 'http://USBLRGAURKAMAT1:8082' // Without Ending '/'
};
