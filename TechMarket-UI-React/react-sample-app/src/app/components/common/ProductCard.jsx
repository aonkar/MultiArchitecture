import React from 'react';
import PropTypes from 'prop-types';
import { NavLink } from 'react-router-dom';
import Img from 'react-image';

import 'stylesheets/components/common/ProductCard.scss';

const ProductCard = ({ productDetails }) => {
	const {
		title,
		description,
		originalPrice,
		finalPrice,
		productId,
		productImage
	} = productDetails;

	return (
		<div className="product-card card">
			<NavLink to={`/pdp/${productId}`} exact activeClassName="active">
				<div className="product-image">
					<Img
						className="d-block w-100"
						src={[
							`${ASSETS_PUBLIC_PATH}/images/products/${productImage}`,
							`${ASSETS_PUBLIC_PATH}/images/image-not-found.png`
						]}
					/>
				</div>

				<div className="card-body">
					<h4 className="card-title">{title}</h4>
					<p className="card-text">{description}</p>
					<p className="product-price">
						<span className="final">${finalPrice}</span>

						<span className="strike-through">
							<span className="original">${originalPrice}</span>
						</span>
					</p>
				</div>
			</NavLink>
		</div>
	);
};

ProductCard.propTypes = {
	productDetails: PropTypes.object.isRequired
};

export default ProductCard;
