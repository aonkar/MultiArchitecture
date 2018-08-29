import { applyMiddleware, combineReducers, createStore } from 'redux';
import logger from 'redux-logger';
import thunk from 'redux-thunk';
import { reducer as formReducer } from 'redux-form';

import * as rootReducer from 'app/reducers/index';

const combinerRootReducer = combineReducers({
	...rootReducer,
	form: formReducer
});

export default function configureStore() {
	const store = createStore(combinerRootReducer, applyMiddleware(thunk, logger));
	return store;
}
