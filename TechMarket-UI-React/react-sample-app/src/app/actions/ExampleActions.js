import EC from 'constants/ExampleConstants';
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

export const getExamplePageData = userName => (dispatch, getState) => {
	setTimeout(() => {
		console.log('setTimeout Completed');
		dispatch({
			type: EC.USERNAME_UPDATE,
			payload: userName
		});
	}, 2000);
};

export function foo() {}

export const getExamplePageDataAjax = args => (dispatch, getState) => {
	ApiUtils.makeAjaxRequest(
		{
			url: 'api/example',
			method: 'GET'
		},
		res => {
			dispatch({
				type: EC.USERNAME_UPDATE,
				payload: res.message
			});
		},
		err => {
			dispatch({
				type: EC.USERNAME_UPDATE,
				payload: 'Some Error Occured'
			});
		}
	);
};
