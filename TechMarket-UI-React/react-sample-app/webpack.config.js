/* eslint-disable global-require */
const { resolve } = require('path');
const PROJECT_CONFIG = require('./project-config.js');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

const extractCSS = new ExtractTextPlugin(`${PROJECT_CONFIG.CSS_FOLDER}/bundle.[name].css`);

module.exports = env => {
	console.log('>>>> env  : ', env);

	const IS_PRODUCTION_MODE = !env.dev;
	const IS_ANALYSE_BUILD = env.analyse || false;
	const IS_MOCK_SERVER = env.mock || true;
	const IS_ABSOLUTE_API_PATH = env.absoluteApiPath || false;
	const APP_PUBLIC_PATH = PROJECT_CONFIG.REACT_PUBLIC_PATH;

	const WEBPACK_UTILS = require('./webpack-utils')({
		IS_PRODUCTION_MODE,
		IS_ANALYSE_BUILD,
		IS_MOCK_SERVER,
		IS_ABSOLUTE_API_PATH,
		APP_PUBLIC_PATH
	});

	// Webpack rules
	const RULES = require('./webpack-settings/webpack.rules')({
		IS_PRODUCTION_MODE,
		extractCSS,
		APP_PUBLIC_PATH
	});

	// Webpack plugins
	const PLUGINS = require('./webpack-settings/webpack.plugins')({
		IS_PRODUCTION_MODE,
		IS_ANALYSE_BUILD,
		IS_ABSOLUTE_API_PATH,
		APP_PUBLIC_PATH,
		extractCSS
	});

	// Webpack Dev-Server
	const devServer = require('./webpack-settings/webpack.dev-server')({
		IS_MOCK_SERVER,
		APP_PUBLIC_PATH
	});

	WEBPACK_UTILS.printDetails();

	const config = {
		context: resolve(PROJECT_CONFIG.SOURCE_FOLDER),
		entry: {
			app: PROJECT_CONFIG.ENTRY_POINT,
			vendor: [
				'react',
				'react-dom',
				'prop-types',
				'redux',
				'react-router',
				'react-redux',
				'react-router-dom',
				'redux-thunk',
				'jquery',
				'popper.js',
				'toastr',
				'bootstrap/dist/js/bootstrap.min.js'
			]
		},
		output: {
			// path: resolve(`${PROJECT_CONFIG.WEB_ROOT}${PROJECT_CONFIG.REACT_PUBLIC_PATH}`),
			path: resolve(PROJECT_CONFIG.WEB_ROOT, PROJECT_CONFIG.REACT_PUBLIC_PATH),
			filename: `${PROJECT_CONFIG.JS_FOLDER}/bundle.[name].js`,
			// publicPath: `${PROJECT_CONFIG.REACT_PUBLIC_PATH}`, // Should not use if using webpack-dev-server [current config]
			publicPath: `/${APP_PUBLIC_PATH}`,
			pathinfo: !IS_PRODUCTION_MODE
		},
		devtool: 'source-map',
		module: {
			rules: RULES
		},
		plugins: PLUGINS,
		resolve: {
			alias: {
				components: resolve(__dirname, 'src', 'app', 'components'),
				constants: resolve(__dirname, 'src', 'app', 'constants'),
				actions: resolve(__dirname, 'src', 'app', 'actions'),
				utils: resolve(__dirname, 'src', 'app', 'utils'),
				stylesheets: resolve(__dirname, 'src', 'stylesheets'),
				app: resolve(__dirname, 'src', 'app')
			},
			extensions: ['.js', '.jsx', '.scss']
		},
		stats: {
			children: false
		},
		devServer
	};

	if (env && env.debug) {
		console.log('wepack.config: ', config);
	}

	return config;
};
