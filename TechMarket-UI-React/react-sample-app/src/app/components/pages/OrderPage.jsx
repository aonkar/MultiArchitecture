import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';


// import { fetchOrderDetails } from 'actions/OrderPageActions';

import 'stylesheets/pages/OrderPage.scss';

class OrderPage extends React.Component {
	// componentDidMount() {
	// 	const { match } = this.props;
	// 	this.props.dispatch(fetchOrderDetails(match.params.orderId));
	// }
	render() {
		const { orderDetails } = this.props;
		const { isDataLoaded, isError } = orderDetails;
		return (
			<article className="order-page container">
				{!isDataLoaded && orderDetails.orderID &&
					<div>Loading Data from Server</div>
				}

				{isDataLoaded && isError &&
					<div>Error occuried while fetching data from Back End</div>
				}
				<h2 className="text-center">Your order is confirmed</h2>
				<p className="text-center">Your order is confirmed. Please check your registered email for the invoice</p>
				{/* <div className="order-page_details">
					<div className="row">
						<div className="order-page_details-image col-md-2 col-sm-12">
							<img
								src={`${ASSETS_PUBLIC_PATH}/images/products/${orderDetail.productImage}`}
								alt="product"
								className="img-fluid" />
						</div>
						<div className="order-page_details-description col-md-10 col-sm-12">
							<h5>
								<a
									href={orderDetail.productLink}
									className="order-page_details-description_link">{orderDetail.productTtile}
								</a>
							</h5>
							{match.params && match.params.orderId &&
								<span>Ordered ID: {orderDetail.orderID} | </span>
							}
							<span>Ordered On: {orderDetail.orderedOn} | </span>
							<span>Current Status: {orderDetail.currentStatus}</span>
							<div className="order-page_details-description_track">
								<a href="xyz">Track Order</a>
							</div>
						</div>
					</div>
				</div> */}
			</article>
		);
	}
}

const mapStateToProps = (state, ownProps) => {
	return {
		orderDetails: state.orderDetails
	};
};

OrderPage.defaultProps = {
	// match: {}
};

OrderPage.propTypes = {
	// match: PropTypes.object,
	// dispatch: PropTypes.func.isRequired,
	orderDetails: PropTypes.object.isRequired
};

export default connect(mapStateToProps)(OrderPage);

