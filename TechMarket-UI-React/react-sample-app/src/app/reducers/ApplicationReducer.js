import AC from 'constants/ApplicationConstants';

export const initialState = {
	isDataLoaded: false
};

export default function applicationReducer(previousState = initialState, action) {
	switch (action.type) {
		case AC.APPLICATION_SET_STATE:
			return {
				...previousState,
				...action.payload.appMetaData
			};

		default:
			return previousState;
	}
}
