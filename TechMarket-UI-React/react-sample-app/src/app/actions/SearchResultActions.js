import SRC from 'constants/SearchResultConstants';
import ApiUtils from 'utils/api-utils';

export const getSearchResult = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: `/api/products?searchTerm=${args}`
		},
		res => {
			dispatch({
				type: SRC.SEARCH_RESULT_SUCCEED,
				payload: res.data
			});
		},
		err => {
			dispatch({
				type: SRC.SEARCH_RESULT_FAILED,
				payload: err
			});
		}
	);
};

export function foo() {}
