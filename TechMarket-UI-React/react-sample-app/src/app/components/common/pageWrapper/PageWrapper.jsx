import React, { Component } from 'react';
// import { connect } from 'react-redux';

function pageWrapper(PageComponent, options) {
	console.log('>>>> PageComponent: ', PageComponent);
	console.log('>>>> options: ', options);
	return class PageWrapper extends Component {
		render() {
			console.log('>>>> this.props : ', this.props);
			return (
				<div className="PageWrapper">
					<h2>PageWrapper Component</h2>
					<PageComponent {...this.props} />
				</div>
			);
		}
	};
}

export default pageWrapper;
