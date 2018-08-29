import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';

import configureStore from 'utils/store-utils';

import './dependency';
import ApplicationLayout from './ApplicationLayout';

const mainStore = configureStore();

ReactDOM.render(
	<Provider store={mainStore}>
		<ApplicationLayout />
	</Provider>,
	document.getElementById('app-root')
);
