import SRC from 'constants/CartPageConstants';

export const initialState = {
	isDataLoaded: false,
	isError: false,
	message: '',
	cartDetails: {}
};

export default function cartPageReducer(previousState = initialState, action) {
	switch (action.type) {
		case SRC.CART_DATA_SUCCEED:
			return {
				...previousState,
				isDataLoaded: true,
				isError: false,
				message: '',
				cartDetails: action.payload
			};
		case SRC.CART_DATA_FAILED:
			return {
				...previousState,
				isDataLoaded: true,
				isError: true,
				message: action.payload,
				cartDetails: []
			};
		default:
			return previousState;
	}
}
