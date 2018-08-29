import AA from 'constants/AuthenticationConstants';
import AC from 'constants/ApplicationConstants';

export const initialState = {
	data: {}
};

export default function authenticationReducer(previousState = initialState, action) {
	switch (action.type) {
		case AA.USER_SIGNIN_SUCCESS:
			return {
				data: action.payload
			};
		case AC.APPLICATION_SET_TOKEN:
			return {
				data: {
					jwt: action.payload.jwt
				}
			};
		default:
			return previousState;
	}
}
