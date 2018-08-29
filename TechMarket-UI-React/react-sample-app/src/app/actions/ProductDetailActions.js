import SRC from 'constants/ProductDetailPageConstants';
import ApiUtils from 'utils/api-utils';

export const fetchProductDetails = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: `/api/products/${args}`
		},
		res => {
			dispatch({
				type: SRC.PRODUCT_DETAIL_SUCCEED,
				payload: res
			});
		},
		err => {
			dispatch({
				type: SRC.PRODUCT_DETAIL_FAILED,
				payload: {
					productId: args,
					err
				}
			});
		}
	);
};

export function foo() {}
