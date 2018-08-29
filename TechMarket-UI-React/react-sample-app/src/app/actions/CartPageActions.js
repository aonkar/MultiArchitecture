import Cookies from 'js-cookie';

import SRC from 'constants/CartPageConstants';
import ApiUtils from 'utils/api-utils';

export const getCartData = args => (dispatch, getState) => {
	const cartId = Cookies.get('cartId');

	ApiUtils.makeAjaxRequest({
		url: `/api/carts/${cartId}`,
		method: 'GET'
	}, (res) => {
		dispatch({
			type: SRC.CART_DATA_SUCCEED,
			payload: res
		});
	}, (err) => {
		dispatch({
			type: SRC.CART_DATA_FAILED,
			payload: err
		});
	});
};

export const cartOperation = (args, options) => (dispatch, getState) => {
	// console.log('args: ', args);
	const currentCartId = Number(Cookies.get('cartId'));

	if (currentCartId) {
		args.cartId = currentCartId;
	}

	ApiUtils.makeAjaxRequest({
		url: '/api/carts',
		method: options.add ? 'POST' : 'DELETE',
		payload: args
	}, (res) => {
		if (options.add) {
			Cookies.set('cartId', res.data.cartId);
			toastr.success(res.message);
		} else {
			dispatch(getCartData(currentCartId));
			toastr.success('Item removed from cart');
		}
	}, (err) => {
		toastr.error(err.message);
	});
};
