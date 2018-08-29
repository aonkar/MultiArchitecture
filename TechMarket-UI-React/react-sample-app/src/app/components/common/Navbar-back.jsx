import React from 'react';
import { NavLink } from 'react-router-dom';

import 'stylesheets/components/common/Navbar';

const Navbar = () => {
	return (
		<nav className="navbar navbar-expand-lg navbar-dark bg-dark">
			<NavLink to="/home" exact activeClassName="active">
				<img
					className="deloitte-logo"
					src="/deloitte-ecommerce/assets/images/deloitte logo/deloitte-logo-wht.png"
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

			<div
				className="collapse navbar-collapse"
				id="navbarSupportedContent"
			>
				<ul className="navbar-nav mr-auto">
					<li className="nav-item active">
						<a className="nav-link" href="#dude">
							Home
							<span className="sr-only">(current)</span>
						</a>
					</li>
					<li className="nav-item">
						<a className="nav-link" href="#dide">
							Link
						</a>
					</li>
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
							Dropdown
						</a>
						<div
							className="dropdown-menu"
							aria-labelledby="navbarDropdown"
						>
							<a className="dropdown-item" href="#dude">
								Action
							</a>
							<a className="dropdown-item" href="#ji">
								Another action
							</a>
							<div className="dropdown-divider" />
							<a className="dropdown-item" href="#hi">
								Something else here
							</a>
						</div>
					</li>
					<li className="nav-item">
						<a className="nav-link disabled" href="#he">
							Disabled
						</a>
					</li>
				</ul>
				<form className="form-inline my-2 my-lg-0">
					<input
						id="search-box"
						className="form-control mr-sm-2"
						type="search"
						placeholder="&#xF002; Search"
						aria-label="Search"
					/>
					{/* <button
						className="btn btn-outline-success my-2 my-sm-0"
						type="submit">
						Search
					</button> */}
				</form>
			</div>
		</nav>
	);
};

export default Navbar;
