import EC from 'constants/ExampleConstants';

export const initialState = {
	userName: 'Ravi Roshan',
	firstName: 'Ravi',
	lastName: 'Roshan'
};

export default function exampleReducer(previousState = initialState, action) {
	switch (action.type) {
		case EC.USERNAME_UPDATE:
			return {
				...previousState,
				userName: action.payload
			};
		default:
			return previousState;
	}
}

