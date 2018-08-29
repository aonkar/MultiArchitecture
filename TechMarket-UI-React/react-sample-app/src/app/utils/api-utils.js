export default class ApiUtils {
	static handleUnAuthorizedUser() {
		sessionStorage.removeItem('jwt');
		window.location.href = `/${APP_PUBLIC_PATH}/signIn`;
	}
	/**
	 * Common utility function to send ajax requests
	 *
	 * @method     makeAjaxRequest
	 * @param      {object}    options
	 * @param      {Function}  onSuccess
	 * @param      {Function}  onFail
	 */
	static makeAjaxRequest(options, onSuccess, onFail) {
		/* eslint-disable no-param-reassign */

		if (options.url.charAt(0) !== '/') {
			// https://webmasters.stackexchange.com/questions/56840/what-is-the-purpose-of-leading-slash-in-html-urls
			options.url = `/${options.url}`;
		}

		if (API_BASE_URL) {
			console.log('API_BASE_URL - TRUE : ', API_BASE_URL);
			options.url = `${API_BASE_URL}${options.url}`;
		}

		// if (process.env.NODE_ENV === 'production') {
		// 	options.url = `${GLOBAL_CONSTANTS.targetAPIServer}${options.url}`;
		// }

		const request = new Request(options.url, {
			method: options.method || 'GET',
			credentials: 'include', // Pass this to pass back JSESSION-ID
			headers: new Headers({
				Accept: 'application/json',
				'Content-Type': 'application/json',
				Authorization: sessionStorage.getItem('jwt') || 'NoTokenFound'
			}),
			body: JSON.stringify(options.payload)
		});

		return fetch(request)
			.then(resp => {
				console.log('resp: ', resp);
				if (resp.status === 401) {
					this.handleUnAuthorizedUser();
				}
				return resp;
			})
			.then(resp => {
				// if (resp.text()) {
				// 	return resp.json();
				// }
				// return {};
				// if (resp.headers.get('Content-Length') > 0) {
				// 	return resp.json();
				// }
				// return {};
				if (resp.status === 200) {
					return resp.json();
				} else if (resp.status === 204) {
					return {};
				}

				return {};
			})
			.then(jsonResponse => {
				if (typeof onSuccess === 'function') {
					onSuccess(jsonResponse);
				}
				return jsonResponse;
			})
			.catch(err => {
				if (typeof onFail === 'function') {
					onFail(err);
				}
				return err;
			});
	}
}
