import AA from 'constants/AuthenticationConstants';
import ApiUtils from 'utils/api-utils';

// export function getExamplePageData(userName) {
// 	return function (dispatch, getState) {
// 		setTimeout(() => {
// 			console.log('setTimeout Completed');
// 			return dispatch({
// 				type: EC.USERNAME_UPDATE,
// 				payload: userName
// 			});
// 		}, 2000);
// 	};
// }

// export const getExamplePageData = userName => (dispatch, getState) => {
// 	setTimeout(() => {
// 		console.log('setTimeout Completed');
// 		dispatch({
// 			type: EC.USERNAME_UPDATE,
// 			payload: userName
// 		});
// 	}, 2000);
// };

export function foo() {}

export const authorizeUser = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: '/api/sign-in',
			method: 'POST',
			payload: args.userObj
		},
		res => {
			console.log('res: ', res);
			sessionStorage.setItem('jwt', res.responseObject.jwt);
			args.history.push('/users');
			dispatch({
				type: AA.USER_SIGNIN_SUCCESS,
				payload: res.responseObject
			});
		},
		err => {
			console.log('err: ', err);
			// dispatch({
			// 	type: EC.USERNAME_UPDATE,
			// 	payload: 'Some Error Occured'
			// });
		}
	);
};

export const invalidateUser = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: '/api/logout',
			method: 'GET'
		},
		res => {
			console.log('res: ', res);
			ApiUtils.handleUnAuthorizedUser();
			// sessionStorage.removeItem('jwt');
			// window.location.href = `/${APP_PUBLIC_PATH}/signIn`;
		},
		err => {
			console.log('err: ', err);
			// dispatch({
			// 	type: EC.USERNAME_UPDATE,
			// 	payload: 'Some Error Occured'
			// });
		}
	);
};
