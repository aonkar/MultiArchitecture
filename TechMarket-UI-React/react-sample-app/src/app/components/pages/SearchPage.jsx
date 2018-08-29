import React from 'react';
import PropTypes from 'prop-types';

import { connect } from 'react-redux';

import ProductCard from 'components/common/ProductCard';
import ScrollToTopOnMount from 'components/common/ScrollToTopOnMount';
import LoaderCmp from 'components/common/LoaderCmp';

import { getSearchResult } from 'actions/SearchResultActions';

import 'stylesheets/pages/SearchPage.scss';

class SearchPage extends React.Component {
	componentDidMount() {
		this.props.dispatch(
			getSearchResult(this.props.match.params.searchKeyword)
		);
	}

	componentWillReceiveProps(nextProps) {
		// console.log('componentWillReceiveProps nextProps : ', nextProps);
		// console.log('componentWillReceiveProps this props : ', this.props);

		// Below condition is required if user searches something from Search Page Input only and Infite Loop
		const oldSearchkeyword = this.props.match.params.searchKeyword;
		const newSearchKeyword = nextProps.match.params.searchKeyword;
		if (oldSearchkeyword !== newSearchKeyword) {
			this.props.dispatch(getSearchResult(newSearchKeyword));
		}
	}

	render() {
		const { match, searchResult } = this.props;
		const { isDataLoaded, isError, productList } = searchResult;
		const itemCount = productList && productList.length;

		return (
			<article className="search-page container">
				<ScrollToTopOnMount />
				{!isDataLoaded && <LoaderCmp />}

				{isDataLoaded &&
					!isError && (
						<div className="result-info">
							<p>
								{`${itemCount || 'No'} result found for`}
								<span className="searched-keyword">
									{' '}
									{`${match.params.searchKeyword}`}
								</span>
							</p>
						</div>
					)}

				{isDataLoaded &&
					isError && (
						<h3>
							Error occuried while fetching data from Back End
						</h3>
					)}

				{isDataLoaded &&
					itemCount > 0 && (
						<div className="container">
							<div className="row">
								{productList.map(obj => {
									return (
										<ProductCard
											key={obj.title}
											productDetails={obj}
										/>
									);
								})}
							</div>
						</div>
					)}
			</article>
		);
	}
}

SearchPage.propTypes = {
	match: PropTypes.object.isRequired
};

const mapStateToProps = (state, ownProps) => {
	return {
		searchResult: state.searchResult
	};
};

SearchPage.propTypes = {
	dispatch: PropTypes.func.isRequired,
	searchResult: PropTypes.object.isRequired
};

export default connect(mapStateToProps)(SearchPage);
