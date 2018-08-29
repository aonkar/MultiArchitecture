const { resolve } = require('path');
const webpack = require('webpack');

const HtmlWebpackPlugin = require('html-webpack-plugin');
const HtmlWebpackExcludeAssetsPlugin = require('html-webpack-exclude-assets-plugin');
const HtmlWebpackHarddiskPlugin = require('html-webpack-harddisk-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const Visualizer = require('webpack-visualizer-plugin');

const CaseSensitivePathsPlugin = require('case-sensitive-paths-webpack-plugin');
const PROJECT_CONFIG = require('../project-config.js');

module.exports = function getPlugins(options) {
	// let ASSETS_PUBLIC_PATH;
	// const { APP_PUBLIC_PATH } = options;

	// if (APP_PUBLIC_PATH === '/') {
	// 	// Only Root path [Private Hosting]
	// 	ASSETS_PUBLIC_PATH = '/assets';
	// } else if (APP_PUBLIC_PATH.charAt(APP_PUBLIC_PATH.length - 1) === '/') {
	// 	ASSETS_PUBLIC_PATH = `${APP_PUBLIC_PATH}assets`;
	// } else {
	// 	ASSETS_PUBLIC_PATH = `${APP_PUBLIC_PATH}/assets`;
	// }

	// console.log('>>>> ASSETS_PUBLIC_PATH: ', ASSETS_PUBLIC_PATH);

	let API_BASE_URL = false;

	if (options.IS_ABSOLUTE_API_PATH) {
		API_BASE_URL = JSON.stringify(PROJECT_CONFIG.API_BASE_URL);
	}

	console.log('>>>> API_BASE_URL: ', API_BASE_URL);

	const plugins = [
		new CaseSensitivePathsPlugin(),
		new webpack.NoEmitOnErrorsPlugin(),
		new webpack.DefinePlugin({
			VERSION: JSON.stringify('Some Random Version'), // This Returns a String else 'PURE TEXT WITHOUT  quote'
			ASSETS_PUBLIC_PATH: JSON.stringify(`/${options.APP_PUBLIC_PATH}/assets`),
			IS_MOCK_SERVER: options.IS_MOCK_SERVER,
			API_BASE_URL,
			APP_PUBLIC_PATH: JSON.stringify(options.APP_PUBLIC_PATH)
		}),
		new webpack.ProvidePlugin({
			$: 'jquery',
			jQuery: 'jquery',
			Popper: 'popper.js',
			toastr: 'toastr'
		}),
		options.extractCSS,
		new CopyWebpackPlugin([
			{
				from: 'assets',
				to: 'assets'
			}
		]),

		new webpack.optimize.CommonsChunkPlugin({
			name: 'vendor'
		}),
		new HtmlWebpackPlugin({
			appTitle: PROJECT_CONFIG.APP_TITLE,
			publicPath: `/${options.APP_PUBLIC_PATH}`,
			isProductionMode: options.IS_PRODUCTION_MODE,
			filename: 'index.html',
			template: 'index.template.ejs',
			favicon: resolve(__dirname, '..', 'src', 'assets', 'images', 'favicon.ico'),
			alwaysWriteToDisk: true
		}),
		new HtmlWebpackExcludeAssetsPlugin(),
		new HtmlWebpackHarddiskPlugin()
	];

	if (options.IS_ANALYSE_BUILD) {
		plugins.push(
			new Visualizer({
				filename: '../build-analysis/statistics.html'
			})
		);
	}

	return plugins;
};
