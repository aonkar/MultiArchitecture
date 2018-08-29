// Libraries
import React from 'react';
import Loader from 'react-loaders';

// Styles
import 'stylesheets/components/common/LoaderCmp';

export default class LoaderCmp extends React.Component {
	render() {
		return (
			<div className="LoaderCmp">
				{/* <p>Loading Data from Server</p>
				<div className="parent">
					<div className="child">Child</div>
				</div> */}
				<Loader type="line-scale" active />
			</div>
		);
	}
}
