import SRC from 'constants/OrderPageConstants';
import ApiUtils from 'utils/api-utils';

export const fetchOrderDetails = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: `api/order-page.json?orderid=${args}`,
			method: 'GET'
		},
		res => {
			dispatch({
				type: SRC.ORDER_DETAIL_SUCCEED,
				payload: res
			});
		},
		err => {
			dispatch({
				type: SRC.ORDER_DETAIL_FAILED,
				payload: err
			});
		}
	);
};

export function name(params) {}
