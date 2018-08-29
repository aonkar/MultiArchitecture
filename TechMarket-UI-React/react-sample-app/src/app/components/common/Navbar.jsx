import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { NavLink } from 'react-router-dom';

import { invalidateUser } from 'actions/AuthenticationActions';

import 'stylesheets/components/common/Navbar';

class Navbar extends Component {
	constructor(props) {
		super(props);
		this.handleLogout = this.handleLogout.bind(this);
		this.getMenuItems = this.getMenuItems.bind(this);
	}

	getMenuItems() {
		const menuItems = [];
		if (this.props.authentication.jwt) {
			menuItems.push(
				<NavLink to="/users/view" key="viewuser" className="nav-link">
					View User
				</NavLink>
			);
			menuItems.push(
				<NavLink to="/users/add" key="adduser" className="nav-link">
					Add User
				</NavLink>
			);
		}

		return menuItems;
	}

	handleLogout(e) {
		console.log('handleLogout called: ');
		// api/logout - GET
		this.props.dispatch(invalidateUser());
		e.preventDefault();
	}

	render() {
		const { firstname, jwt } = this.props.authentication;
		console.log('firstname: ', firstname);
		console.log('jwt: ', jwt);

		return (
			<nav className="navbar navbar-expand-lg navbar navbar-dark">
				<NavLink to="/users" exact activeClassName="active">
					<img
						className="deloitte-logo"
						src={`${ASSETS_PUBLIC_PATH}/images/logo/deloitte-wht.png`}
						alt=""
					/>
				</NavLink>
				<button
					className="navbar-toggler"
					type="button"
					data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent"
					aria-expanded="false"
					aria-label="Toggle navigation"
				>
					<span className="navbar-toggler-icon" />
				</button>

				<div className="collapse navbar-collapse" id="navbarSupportedContent">
					<div className="navbar-nav mr-auto" />
					{/* <SearchInput history={propsObj.history} /> */}
					<ul className="navbar-nav">
						{this.getMenuItems()}
						{jwt &&
							<li className="nav-item dropdown">
								<a
									className="nav-link dropdown-toggle"
									href="#dude"
									id="navbarDropdown"
									role="button"
									data-toggle="dropdown"
									aria-haspopup="true"
									aria-expanded="false"
								>
									<i className="fa fa-user" />
									<span>Hi {firstname || 'Guest'}</span>
								</a>
								<div
									className="dropdown-menu dropdown-menu-right"
									aria-labelledby="navbarDropdown"
								>
									<NavLink to="/update-profile" className="dropdown-item">
										<i className="fa fa-user" />
										<span>Update Profile</span>
									</NavLink>

									<div className="dropdown-divider" />

									<a
										href={`/${APP_PUBLIC_PATH}/signIn`}
										className="dropdown-item"
										onClick={this.handleLogout}
									>
										<i className="fa fa-sign-out" />
										<span>{jwt ? 'Logout' : 'Sign-In'}</span>
									</a>
								</div>
							</li>
						}
					</ul>
				</div>
			</nav>
		);
	}
}

Navbar.propTypes = {
	// history: PropTypes.object.isRequired,
	dispatch: PropTypes.func.isRequired,
	authentication: PropTypes.object.isRequired
};

function mapStateToProps(state) {
	return {
		authentication: state.authentication.data
	};
}
export default connect(mapStateToProps)(Navbar);
