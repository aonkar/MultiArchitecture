import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import PropTypes from 'prop-types';

import { connect } from 'react-redux';

import { checkAndSetToken } from 'actions/ApplicationActions';

import ErrorBoundary from 'components/common/ErrorBoundary';
import Header from 'components/common/Header';
import Main from 'components/common/Main';
import Footer from 'components/common/Footer';

class ApplicationLayout extends React.Component {
	componentDidMount() {
		this.props.dispatch(checkAndSetToken());
	}

	render() {
		return (
			<BrowserRouter basename={`/${APP_PUBLIC_PATH}`}>
				<div className="application-layout">
					<ErrorBoundary>
						<Header />
						<Main />
						<Footer />
					</ErrorBoundary>
				</div>
			</BrowserRouter>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	return state;
};

ApplicationLayout.propTypes = {
	dispatch: PropTypes.func.isRequired
};

export default connect(mapStateToProps)(ApplicationLayout);
