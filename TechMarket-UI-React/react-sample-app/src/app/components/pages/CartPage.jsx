import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
// import { NavLink } from 'react-router-dom';
// import Img from 'react-image';

import LoaderCmp from 'components/common/LoaderCmp';
import CartItem from 'components/common/CartItem';

import ScrollToTopOnMount from 'components/common/ScrollToTopOnMount';

import { getCartData, cartOperation } from 'actions/CartPageActions';

import 'stylesheets/pages/CartPage.scss';

class CartPage extends React.Component {
	constructor(props) {
		super(props);
		this.confirmOrder = this.confirmOrder.bind(this);
		this.removeCartItem = this.removeCartItem.bind(this);
	}

	componentDidMount() {
		this.props.dispatch(getCartData());
	}

	confirmOrder() {
		this.props.history.push('/orders/4rd-order');
	}

	removeCartItem(itemId) {
		// console.log('removeCartItem called : ', itemId);

		const cartObj = {
			itemId: Number(itemId)
		};

		this.props.dispatch(cartOperation(cartObj, {
			delete: true
		}));
	}

	render() {
		const { cartData } = this.props;
		const { isDataLoaded, isError, cartDetails } = cartData;
		const { cartItems } = cartDetails;
		const itemCount = (cartItems) ? cartItems.length : 0;
		return (
			<div className="container">
				<ScrollToTopOnMount />
				<article className="cart-page">
					<div className="d-flex col-12 justify-content-center align-items-center cart-page_header">Shopping Cart</div>
					<div>
						{itemCount < 0 &&
							'Your cart is empty'
						}
					</div>

					{!isDataLoaded &&
						<LoaderCmp />
					}

					{isDataLoaded && isError &&
						<div>Error occuried while fetching data from Back End</div>
					}

					{isDataLoaded && itemCount > 0 &&
						<div>
							{cartItems.map(obj => (
								<CartItem
									key={obj.itemID}
									productDetails={obj}
									removeCartItem={this.removeCartItem}
								/>))
							}
							<div className="d-flex justify-content-end align-items-center cart-page_checkout">
								Total Price: ${cartDetails.totalPrice}
								<button className="btn cart-page_checkout_btn" onClick={this.confirmOrder}>
									Checkout
								</button>
							</div>
						</div>
					}

				</article>
			</div>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	return {
		cartData: state.cartData
	};
};

CartPage.propTypes = {
	dispatch: PropTypes.func.isRequired,
	history: PropTypes.object.isRequired,
	cartData: PropTypes.object.isRequired
};

export default connect(mapStateToProps)(CartPage);
