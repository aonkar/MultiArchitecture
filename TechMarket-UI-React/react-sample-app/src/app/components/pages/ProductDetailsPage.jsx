import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import Img from 'react-image';

import LoaderCmp from 'components/common/LoaderCmp';
import ScrollToTopOnMount from 'components/common/ScrollToTopOnMount';

import { fetchProductDetails } from 'actions/ProductDetailActions';
import { cartOperation } from 'actions/CartPageActions';
import 'stylesheets/pages/ProductDetailsPage.scss';

class ProductDetailsPage extends React.Component {
	constructor(props) {
		super(props);
		this.handleAddToCart = this.handleAddToCart.bind(this);
		this.quantityChanged = this.quantityChanged.bind(this);
		this.state = {
			productQuantity: 1
		};
	}

	componentDidMount() {
		const { match } = this.props;
		this.props.dispatch(fetchProductDetails(match.params.productId));
	}

	handleAddToCart() {
		const { productId } = this.props.match.params;

		const cartObj = {
			quantity: Number(this.state.productQuantity),
			productId: Number(productId)
		};

		this.props.dispatch(
			cartOperation(cartObj, {
				add: true
			})
		);
	}

	quantityChanged(e) {
		this.setState({
			productQuantity: e.target.value
		});
	}

	render() {
		const { productDetails } = this.props;

		const { isDataLoaded, isError } = productDetails.meta;
		const productData = productDetails.data;

		return (
			<article className="product-details container">
				<ScrollToTopOnMount />
				{!isDataLoaded && <LoaderCmp />}

				{isDataLoaded &&
					isError && (
						<div>
							Error occuried while fetching data from Back End
						</div>
					)}

				{isDataLoaded &&
					!isError && [
						<div
							className="row"
							key={`detail-${productData.productId}`}
						>
							<div className="col-md-5 col-sm-12 product-details_image">
								<Img
									className="img-fluid"
									src={[
										`${ASSETS_PUBLIC_PATH}/images/products/${
											productData.productImage
										}`,
										`${ASSETS_PUBLIC_PATH}/images/image-not-found.png`
									]}
								/>
							</div>
							<div className="d-flex flex-column align-items-start col-md-7 col-sm-12 product-details_content">
								<div className="mb-auto">
									<h1>{productData.productTitle}</h1>
									{/* <p>Product ID: <span> {productData.productId}</span></p> */}
								</div>
								<p className="product-price">
									<span className="final">
										${productData.finalPrice}
									</span>
									<span className="strike-through">
										<span className="original">
											${productData.originalPrice}
										</span>
									</span>
								</p>
								<div className="d-flex product-details_actions">
									<label
										htmlFor="items"
										className="d-flex justify-content-center align-items-center"
									>
										Quantity:
										<select
											className="form-control ml-3"
											id="items"
											onChange={this.quantityChanged}
										>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
										</select>
									</label>
									<button
										type="button"
										className="btn col-md-2 btn-primary product-details_add mt-2"
										onClick={this.handleAddToCart}
									>
										<i
											className="fa fa-shopping-cart mr-2"
											aria-hidden="true"
										/>
										Add
									</button>
								</div>
							</div>
						</div>,
						<div
							className="row"
							key={`description-${productData.productId}`}
						>
							<div className="col-12 product-details_description_title">
								Product Description
							</div>
							<p>{productData.description}</p>
						</div>
					]}
			</article>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	return {
		productDetails:
			state.productDetails[ownProps.match.params.productId] ||
			state.productDetails.default
	};
};

ProductDetailsPage.propTypes = {
	match: PropTypes.object.isRequired,
	dispatch: PropTypes.func.isRequired,
	productDetails: PropTypes.object.isRequired
};

export default connect(mapStateToProps)(ProductDetailsPage);
