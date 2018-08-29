import React, { Component } from 'react';
import { Field, reduxForm } from 'redux-form';
import PropTypes from 'prop-types';
import { addUser } from 'actions/UsersActions';
import { connect } from 'react-redux';

import './AddUser.scss';

class AddUser extends Component {
	constructor(props) {
		super(props);
		this.handleAddUser = this.handleAddUser.bind(this);
	}

	handleAddUser(userObj) {
		console.log('userObj: ', userObj);
		userObj.category = '';
		userObj.isUserActive = true;
		this.props.dispatch(addUser(userObj));
	}

	render() {
		const { handleSubmit } = this.props;
		return (
			<div className="AddUser">
				<h2 className="page-title">
					<i className="fa fa-address-book" /> Add User
				</h2>
				<div className="AddUser-form-wrapper">
					<form onSubmit={handleSubmit(this.handleAddUser)}>
						<div className="form-group row">
							<label htmlFor="userName" className="col-sm-12 col-md-5 col-form-label">
								Username
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="userName"
									component="input"
									placeholder="Username"
									className="form-control"
									type="text"
									required
								/>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="password" className="col-sm-12 col-md-5 col-form-label">
								Password
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="password"
									component="input"
									placeholder="Password"
									className="form-control"
									type="password"
									required
								/>
							</div>
						</div>

						<div className="form-group row">
							<label
								htmlFor="firstname"
								className="col-sm-12 col-md-5 col-form-label"
							>
								First Name
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="firstname"
									component="input"
									placeholder="First Name"
									className="form-control"
									type="text"
									required
								/>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="lastname" className="col-sm-12 col-md-5 col-form-label">
								Last Name
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="lastname"
									component="input"
									placeholder="Last Name"
									className="form-control"
									type="text"
									required
								/>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="email" className="col-sm-12 col-md-5 col-form-label">
								User Email
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="email"
									component="input"
									placeholder="User Email"
									className="form-control"
									type="email"
									required
								/>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="mobile" className="col-sm-12 col-md-5 col-form-label">
								User Mobile
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="mobile"
									component="input"
									placeholder="User Mobile"
									className="form-control"
									type="number"
									required
								/>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="age" className="col-sm-12 col-md-5 col-form-label">
								User Age
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="age"
									component="input"
									placeholder="User Age"
									className="form-control"
									type="text"
									required
								/>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="role" className="col-sm-12 col-md-5 col-form-label">
								Role
							</label>
							<div className="col-sm-12 col-md-7">
								<Field
									name="role"
									component="input"
									placeholder="Role"
									className="form-control"
									type="text"
									required
								/>
							</div>
						</div>

						<div className="row submit-wrapper">
							<div className="col-sm-12 d-flex justify-content-center">
								<button type="submit" className="btn btn-outline-dark">
									<i className="fa fa-check-circle" aria-hidden="true" />
									<span> Confirm</span>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		);
	}
}

AddUser.propTypes = {
	handleSubmit: PropTypes.func.isRequired,
	dispatch: PropTypes.func.isRequired
};

const AddUserForm = reduxForm({
	// a unique name for the form
	form: 'adduser'
})(AddUser);

// export default AddUserForm;

export default connect()(AddUserForm);
