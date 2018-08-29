import React, { Component } from 'react';
import Img from 'react-image';

class PageNotFound extends Component {
	render() {
		return (
			<div className="PageNotFound container">
				<Img
					className="d-block w-100"
					src={[
						`${ASSETS_PUBLIC_PATH}/images/404.gif`,
						`${ASSETS_PUBLIC_PATH}/images/image-not-found.png`
					]}
				/>
			</div>
		);
	}
}

export default PageNotFound;
