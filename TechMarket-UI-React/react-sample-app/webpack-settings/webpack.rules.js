const PROJECT_CONFIG = require('../project-config.js');

module.exports = function getRules(options) {
	const rules = [
		// ES-Lint on continuous build
		{
			enforce: 'pre',
			test: /\.(js|jsx)$/,
			exclude: /node_modules/,
			loader: 'eslint-loader',
			options: {
				cache: false
			}
		},
		// Our Javascript/JSX (bundle into one)
		{
			test: /\.(js|jsx)$/,
			use: ['babel-loader'],
			exclude: /node_modules/
		},
		// SCSS-CSS extract to seperate file
		{
			test: /\.(css|scss)$/,
			use: options.extractCSS.extract({
				fallback: 'style-loader',
				// resolve-url-loader may be chained before sass-loader if necessary
				use: ['css-loader?sourceMap', 'postcss-loader?sourceMap', 'sass-loader?sourceMap'],
				publicPath: `/${options.APP_PUBLIC_PATH}/`
			})
		},
		// Fonts
		{
			test: /\.(eot|svg|ttf|woff|woff2)$/,
			use: [
				{
					loader: 'file-loader',
					options: {
						name: `${PROJECT_CONFIG.ASSETS_FOLDER}/fonts/[name].[ext]`
					}
				}
			]
		},
		// Images
		{
			test: /\.(jpe?g|png|gif|svg|ico)/,
			use: [
				{
					loader: 'file-loader',
					options: {
						name: `${PROJECT_CONFIG.ASSETS_FOLDER}/images/[name].[ext]`
					}
				}
			]
		},
		// Videos
		{
			test: /\.(mp4)/,
			use: [
				{
					loader: 'file-loader',
					options: {
						name: `${PROJECT_CONFIG.ASSETS_FOLDER}/videos/[name].[ext]`
					}
				}
			]
		},
		// PDFs
		{
			test: /\.(pdf)/,
			use: [
				{
					loader: 'file-loader',
					options: {
						name: `${PROJECT_CONFIG.ASSETS_FOLDER}/docs/[name].[ext]`
					}
				}
			]
		}
		// Exposing jQuery and other packages at windows Object
		// {
		// 	test: require.resolve('jquery'),
		// 	use: [{
		// 		loader: 'expose-loader',
		// 		options: 'jQuery'
		// 	}, {
		// 		loader: 'expose-loader',
		// 		options: '$'
		// 	}]
		// },
	];

	return rules;
};
