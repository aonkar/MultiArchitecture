import React from 'react';
import PropTypes from 'prop-types';

export default class SearchInput extends React.Component {
	constructor(props) {
		super(props);
		this.handleFormSubmit = this.handleFormSubmit.bind(this);
	}

	handleFormSubmit(e) {
		e.preventDefault();
		// console.log('-----', this.textInput.value);
		if (this.textInput.value.trim().length >= 3) {
			this.props.history.push(`/search/${this.textInput.value}`);
		}
	}

	render() {
		return (
			<form
				className="form-inline my-2 my-lg-0"
				onSubmit={this.handleFormSubmit}
			>
				<input
					id="search-box"
					ref={input => {
						this.textInput = input;
					}}
					className="form-control mr-sm-2"
					type="search"
					placeholder="&#xF002; Search Items"
					aria-label="Search"
				/>
			</form>
		);
	}
}

SearchInput.propTypes = {
	history: PropTypes.object.isRequired
};
