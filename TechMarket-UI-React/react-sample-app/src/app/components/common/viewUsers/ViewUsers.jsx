import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getAllUsers, deleteUser } from 'actions/UsersActions';

import './ViewUsers.scss';

class ViewUsers extends Component {
	constructor(props) {
		super(props);
		this.handleEditUser = this.handleEditUser.bind(this);
		this.handleDeleteUser = this.handleDeleteUser.bind(this);
		this.userOperationFormatter = this.userOperationFormatter.bind(this);
	}

	componentDidMount() {
		this.props.dispatch(getAllUsers());
	}

	handleEditUser(rowIndex) {
		this.props.history.push(`${this.props.match.path}/edit/${rowIndex}`);
	}

	handleDeleteUser(row) {
		console.log('row: ', row);
		this.props.dispatch(deleteUser(row.userId));
		console.log('handleDeleteUser this : ', this);
	}

	userOperationFormatter(cell, row, enumObject, rowIndex) {
		return (
			<div className="operation-btn-wrapper">
				<button
					className="btn btn-primary btn-sm"
					onClick={() => {
						console.log('rowIndex: ', rowIndex);
						console.log('enumObject: ', enumObject);
						console.log('row: ', row);
						console.log('cell: ', cell);
						this.handleEditUser(rowIndex);
					}}
				>
					<i className="fa fa-pencil" />
				</button>
				{/* <br />
				<br /> */}
				<button
					className="btn btn-danger btn-sm"
					onClick={() => {
						this.handleDeleteUser(row);
					}}
				>
					<i className="fa fa-trash" />
				</button>
			</div>
		);
	}

	render() {
		console.log('this props : ', this.props);
		return (
			<div className="ViewUsers">
				<h2 className="page-title">
					<i className="fa fa-users" /> View Users
				</h2>
				<div className="pane pane--table1">
					<div className="pane-hScroll">
						<BootstrapTable data={this.props.users} striped hover condensed pagination>
							<TableHeaderColumn
								width="150"
								dataField="userName"
								isKey
								dataAlign="cente"
								dataSort
							>
								User Name
							</TableHeaderColumn>
							<TableHeaderColumn width="150" dataField="firstname" dataSort>
								First Name
							</TableHeaderColumn>
							<TableHeaderColumn width="150" dataField="lastname" dataSort>
								Last Name
							</TableHeaderColumn>
							<TableHeaderColumn width="150" dataField="email" dataSort>
								Email
							</TableHeaderColumn>
							<TableHeaderColumn width="150" dataField="mobile" dataSort>
								Mobile
							</TableHeaderColumn>
							<TableHeaderColumn width="100" dataField="role" dataSort>
								Role
							</TableHeaderColumn>
							<TableHeaderColumn width="100" dataField="category">
								Category
							</TableHeaderColumn>
							<TableHeaderColumn
								width="150"
								dataAlign="cente"
								dataFormat={this.userOperationFormatter}
							>
								Operation
							</TableHeaderColumn>
						</BootstrapTable>
					</div>
				</div>
			</div>
		);
	}
}

ViewUsers.propTypes = {
	match: PropTypes.object.isRequired,
	history: PropTypes.object.isRequired,
	dispatch: PropTypes.func.isRequired,
	users: PropTypes.array.isRequired
};

function mapStateToProps(state) {
	console.log('state: ', state);
	return {
		users: state.users.data
	};
}

export default connect(mapStateToProps)(ViewUsers);
