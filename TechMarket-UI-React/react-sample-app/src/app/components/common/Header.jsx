// Libraries
import React from 'react';
import { Route } from 'react-router-dom';
// Components
import Navbar from 'components/common/Navbar';

// Styles
import 'stylesheets/components/common/Header';

export default class Header extends React.Component {
	render() {
		return (
			<header>
				<Route path="/" component={Navbar} />

				{/* This is not working, will see later - WHY 😏
				<Route >
					<Navbar {...this.props} />
				</Route> */}
			</header>
		);
	}
}
