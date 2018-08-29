import SRC from 'constants/SearchResultConstants';

export const initialState = {
	isDataLoaded: false,
	isError: false,
	message: '',
	productList: []
};

export default function searchPageReducer(previousState = initialState, action) {
	switch (action.type) {
		case SRC.SEARCH_RESULT_SUCCEED:
			return {
				...previousState,
				isDataLoaded: true,
				isError: false,
				message: '',
				productList: action.payload
			};
		case SRC.SEARCH_RESULT_FAILED:
			return {
				...previousState,
				isDataLoaded: true,
				isError: true,
				message: action.payload,
				productList: []
			};
		default:
			return previousState;
	}
}
