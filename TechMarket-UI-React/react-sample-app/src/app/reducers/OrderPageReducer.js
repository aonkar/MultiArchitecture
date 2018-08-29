import SRC from 'constants/OrderPageConstants';

export const initialState = {
	isDataLoaded: false,
	isError: false,
	message: '',
	orderDetail: {}
};

export default function OrderPageReducer(previousState = initialState, action) {
	switch (action.type) {
		case SRC.ORDER_DETAIL_SUCCEED:
			return {
				...previousState,
				isDataLoaded: true,
				isError: false,
				message: '',
				orderDetail: action.payload
			};
		case SRC.ORDER_DETAIL_FAILED:
			return {
				...previousState,
				isDataLoaded: true,
				isError: true,
				message: action.payload,
				orderDetail: {}
			};
		default:
			return previousState;
	}
}
