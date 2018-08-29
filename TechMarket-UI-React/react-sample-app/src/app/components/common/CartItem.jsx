import React from 'react';
import PropTypes from 'prop-types';
import { NavLink } from 'react-router-dom';
import Img from 'react-image';

// import 'stylesheets/components/common/CartItem.scss';

const CartItem = ({ productDetails, removeCartItem }) => {
	const {
		itemId,
		productImage,
		productId,
		title,
		specification
	} = productDetails;

	return (
		<div className="cart-page_item row" key={itemId}>
			<div className="cart-page_item-img col-md-2 col-sm-12">
				<Img
					className="img-fluid"
					src={[
						`${ASSETS_PUBLIC_PATH}/images/products/${productImage}`,
						`${ASSETS_PUBLIC_PATH}/images/image-not-found.png`
					]}
				/>
			</div>
			<div className="cart-page_item-desc d-flex flex-column align-items-start col-md-10 col-sm-12">
				<div className="mb-auto">
					<NavLink
						to={`/pdp/${productId}`}
						exact
						activeClassName="active"
					>
						<h5>{title}</h5>
					</NavLink>
					<div className="cart-page_item-desc_specs">
						{/* <span className="cart-page_item-desc_specs-text">Product ID: {obj.productId} | </span> */}
						<span className="cart-page_item-desc_specs-text">
							Quantity: {specification.noOfItems} |{' '}
						</span>
						<span className="cart-page_item-desc_specs-text">
							Price: ${specification.itemFormattedPrice}
						</span>
					</div>
				</div>
				<div>
					<button
						className="btn cart-page_item-desc_remove"
						onClick={e => removeCartItem(itemId)}
					>
						<i className="fa fa-trash" aria-hidden="true" />
						Remove
					</button>
				</div>
			</div>
		</div>
	);
};

CartItem.propTypes = {
	productDetails: PropTypes.object.isRequired,
	removeCartItem: PropTypes.func.isRequired
};

export default CartItem;
