import SRC from 'constants/ProductDetailPageConstants';

const initialState2 = {
	default: {
		meta: {
			isDataLoaded: false,
			isError: false,
			message: ''
		},
		data: {}
	}
};

export default function ProductDetailPageReducer(previousState = initialState2, action) {
	let newProductArray = previousState;

	switch (action.type) {
		case SRC.PRODUCT_DETAIL_SUCCEED:
			newProductArray = {
				...previousState
			};
			newProductArray[action.payload.productId] = {
				meta: {
					isDataLoaded: true,
					isError: false,
					message: ''
				},
				data: action.payload
			};
			return newProductArray;

		case SRC.PRODUCT_DETAIL_FAILED:
			newProductArray = {
				...previousState
			};
			newProductArray[action.payload.productId] = {
				meta: {
					isDataLoaded: true,
					isError: true,
					message: action.payload.err
				},
				data: {}
			};
			return newProductArray;
		default:
			return previousState;
	}
}
