# README #

## What is this repository for? ##
Front End code repo for E-Commerce web application

## Prerequisites ##
* Node v5.1.0
* NPM v3.3.12

### Technologies ###
The client side application is comprised of the following technologies:

* [ReactJS](http://react-bootstrap.github.io/)
* [Redux](http://redux.js.org/)
* [React-Router](https://github.com/rackt/react-router)
* [Babel](https://babeljs.io/)
* [Webpack](https://webpack.js.org/)
* [NPM](https://www.npmjs.com/) 


### Code & Environment - Setup ###
* [.bash_profile - MacOS Env Variable](https://apple.stackexchange.com/questions/106778/how-do-i-set-environment-variables-on-os-x/229941#229941)
 How to set MacOS environment variable for JDK, Tomcat etc
* [.eslintrc - ESLint Config](https://eslint.org/)
The pluggable linting utility for JavaScript and JSX
* [.sass-lint - SASS Lint Config](https://github.com/sasstools/sass-lint)
The pluggable linting utility for SASS & SCSS
* [.editorconfig - Code Editor Config](http://editorconfig.org/)
EditorConfig helps developers define and maintain consistent coding styles between different editors and IDEs.

##### [SCSS Import - In Webpack](https://github.com/webpack-contrib/sass-loader) #####

> Writing @ import "file" is the same as @import "./file";

The webpack sass-loader uses node-sass' custom importer feature to pass all queries to the webpack resolving engine. Thus you can import your Sass modules from node_modules. 
Just prepend them with a ~ to tell webpack that this is not a relative import:

> @ import "~bootstrap/dist/css/bootstrap";  
> // Searches for file in Webpack Module Path [Node Modules & Other configured aliases] 

It's important to only prepend it with ~, because ~/ resolves to the home directory. webpack needs to distinguish between bootstrap and ~bootstrap because CSS and Sass files have no special syntax for importing relative files. 

### Developer Tools ###
#### [VS Code](https://code.visualstudio.com/) ####
Visual Studio Code is a free code editor redefined and optimized for building and debugging modern web and cloud applications.

##### VS Code - User Preference #####
> { "editor.renderWhitespace": "all" }


##### VS Code Plugins #####
* [Editor Config](https://marketplace.visualstudio.com/items?itemName=EditorConfig.EditorConfig) 
* [JavaScript linter](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint) 
* [SASS linter](https://marketplace.visualstudio.com/items?itemName=glen-84.sass-lint) 

## How to Build & Run ##

Build application for Development Environment  & Local

> npm run build:dev

Build application for Production Environment 

> npm run build

Continuous code, build & run [Development Build on Mock JSON]

> npm run web

Continuous code, build & run [Development Build on Actual Back-End API]

> npm run web:abs

Run Production Build 

> npm run server

Pass extra flags to webpack using [npm script](https://docs.npmjs.com/cli/run-script)

> npm run <cmd> -- --extraParam
> npm run build:dev -- --env.analyse
> npm run build -- --env.mock --env.server='http://www.google.com'
> npm run build -- --env.absoluteApiPath

#### [AWS Build & Deployment Scripts](http://dkart.s3-website.us-east-2.amazonaws.com/) ####

AWS Build 

> npm run build:aws

AWS Build deployment simulation on local server

> npm run server:aws



----------

### Who do I talk to? ###

* [Ravi Roshan](http://www.raviroshan.info/)
