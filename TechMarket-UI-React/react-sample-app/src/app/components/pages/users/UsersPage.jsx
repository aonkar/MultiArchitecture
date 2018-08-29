import React, { Component } from 'react';
import { Route, Redirect, Switch } from 'react-router-dom';
import PropTypes from 'prop-types';

import AddUser from 'components/common/addUser/AddUser';
import EditUser from 'components/common/editUser/EditUser';
import ViewUsers from 'components/common/viewUsers/ViewUsers';

import './UsersPage.scss';

class UsersPage extends Component {
	render() {
		console.log('>>>> this.props.match.path : ', this.props.match.path);

		return (
			<div className="UsersPage container">
				<Switch>
					<Route path={`${this.props.match.path}`} exact component={ViewUsers} />
					<Route path={`${this.props.match.path}/add`} exact component={AddUser} />
					<Route
						path={`${this.props.match.path}/edit/:userId`}
						exact
						component={EditUser}
					/>
					<Redirect to={`${this.props.match.path}`} />
				</Switch>
			</div>
		);
	}
}

UsersPage.propTypes = {
	match: PropTypes.object.isRequired
};

export default UsersPage;
