import AC from 'constants/ApplicationConstants';

export function setApplicationDetails(appMetaData) {
	console.log('Action setApplicationDetails called');
	return {
		type: AC.APPLICATION_SET_STATE,
		payload: {
			appMetaData
		}
	};
}

export function checkAndSetToken(appMetaData) {
	return {
		type: AC.APPLICATION_SET_TOKEN,
		payload: {
			jwt: sessionStorage.getItem('jwt')
		}
	};
}

export function foo() {}
