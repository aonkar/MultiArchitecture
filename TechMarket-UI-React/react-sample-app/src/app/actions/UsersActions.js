import UC from 'constants/UserConstants';
import ApiUtils from 'utils/api-utils';

export function foo() {}

export const getAllUsers = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: '/api/users',
			method: 'GET'
		},
		res => {
			dispatch({
				type: UC.USERS_SET,
				payload: res.responseObject
			});
		},
		err => {
			console.log('err: ', err);
		}
	);
};

export const deleteUser = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: '/api/user',
			method: 'DELETE',
			payload: {
				isUserActive: false,
				userId: args
			}
		},
		res => {
			dispatch(getAllUsers());
			toastr.success(res.message);
		},
		err => {
			toastr.error(err);
		}
	);
};

export const addUser = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: '/api/user',
			method: 'POST',
			payload: args
		},
		res => {
			dispatch(getAllUsers());
			toastr.success(res.message);
		},
		err => {
			toastr.error('Not Added');
		}
	);
};

export const updateUser = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: '/api/user',
			method: 'PUT',
			payload: args
		},
		res => {
			dispatch(getAllUsers());
			toastr.success(res.message);
		},
		err => {
			toastr.error('Not Added');
		}
	);
};
