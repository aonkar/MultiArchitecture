import UC from 'constants/UserConstants';

export const initialState = {
	data: []
};

export default function exampleReducer(previousState = initialState, action) {
	switch (action.type) {
		case UC.USERS_SET:
			return {
				data: action.payload
			};
		default:
			return previousState;
	}
}
