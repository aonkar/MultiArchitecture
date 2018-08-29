import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { authorizeUser } from 'actions/AuthenticationActions';

import './LoginPage.scss';

class LoginPage extends Component {
	constructor(props) {
		super(props);
		sessionStorage.removeItem('jwt');
		this.onLogin = this.onLogin.bind(this);
	}

	onLogin(e) {
		const inputEmail = this.inputEmail.value;
		const inputPassword = this.inputPassword.value;

		// this.props.history.push('/user-list');
		this.props.dispatch(
			authorizeUser({
				userObj: {
					userName: inputEmail,
					password: inputPassword
				},
				history: this.props.history
			})
		);

		e.preventDefault();
	}

	render() {
		console.log('>>>> props : ', this.props);
		return (
			<div className="LoginPage">
				<div className="card card-container">
					<img
						id="profile-img"
						className="profile-img-card"
						src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
						alt="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
					/>
					<p id="profile-name" className="profile-name-card" />
					<form className="form-signin" onSubmit={this.onLogin}>
						<span id="reauth-email" className="reauth-email" />
						<input
							type="text"
							id="inputEmail"
							ref={inputEmail => {
								this.inputEmail = inputEmail;
							}}
							className="form-control"
							placeholder="Email address"
							required
						/>
						<input
							type="password"
							ref={inputPassword => {
								this.inputPassword = inputPassword;
							}}
							className="form-control"
							placeholder="Password"
							required
						/>
						{/* <div id="remember" className="checkbox">
							<label htmlFor="rememberMe">
								<input id="rememberMe" type="checkbox" value="remember-me" />{' '}
								Remember me
							</label>
						</div> */}
						<button
							className="btn btn-lg btn-primary btn-block btn-signin"
							type="submit"
						>
							Sign in
						</button>
					</form>
					{/* <a href="http://www.google.com" className="forgot-password">
						Forgot the password?
					</a> */}
				</div>
			</div>
		);
	}
}

LoginPage.propTypes = {
	history: PropTypes.object.isRequired,
	dispatch: PropTypes.func.isRequired
};

// export default LoginPage;
export default connect()(LoginPage);
