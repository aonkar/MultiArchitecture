import React from 'react';
// import PropTypes from 'prop-types';
import { NavLink } from 'react-router-dom';

import 'stylesheets/pages/HomePage.scss';

export default class HomePage extends React.Component {
	// constructor(props) {
	// 	super(props);
	// }

	render() {
		return (
			<div className="home-page">
				<div
					id="productCarousel"
					className="carousel slide"
					data-ride="carousel"
				>
					<ol className="carousel-indicators">
						<li
							data-target="#productCarousel"
							data-slide-to="0"
							className="active"
						/>
						<li data-target="#productCarousel" data-slide-to="1" />
						<li data-target="#productCarousel" data-slide-to="2" />
					</ol>
					<div className="carousel-inner">
						<div className="carousel-item active">
							<img
								className="d-block w-100 h-100"
								src={`${ASSETS_PUBLIC_PATH}/images/carousel/Phones.jpg`}
								alt="Smart Phones"
							/>
							<div className="carousel-caption d-none d-md-block">
								<h3>Smart Phones</h3>
							</div>
						</div>
						<div className="carousel-item">
							<img
								className="d-block w-100 h-100"
								src={`${ASSETS_PUBLIC_PATH}/images/carousel/headPhones.jpg`}
								alt="Beats Headphones"
							/>
							<div className="carousel-caption d-none d-md-block">
								<h3>Beats Headphones</h3>
							</div>
						</div>
						<div className="carousel-item">
							<img
								className="d-block w-100 h-100"
								src={`${ASSETS_PUBLIC_PATH}/images/carousel/laptop.jpg`}
								alt="ALIENWARE Laptop"
							/>
							<div className="carousel-caption d-none d-md-block">
								<h3>ALIENWARE Laptop</h3>
							</div>
						</div>
					</div>
					<a
						className="carousel-control-prev"
						href="#productCarousel"
						role="button"
						data-slide="prev"
					>
						<span
							className="carousel-control-prev-icon"
							aria-hidden="true"
						/>
						<span className="sr-only">Previous</span>
					</a>
					<a
						className="carousel-control-next"
						href="#productCarousel"
						role="button"
						data-slide="next"
					>
						<span
							className="carousel-control-next-icon"
							aria-hidden="true"
						/>
						<span className="sr-only">Next</span>
					</a>
				</div>

				<div className="container">
					<div className="category-container">
						<h4>HandPicked</h4>
						<p className="banner-subtitle">The best of our brand</p>
						<div className="row">
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/nikon">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/handpicked/nikon.jpg`}
										alt="Nikon"
									/>
								</NavLink>
							</div>
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/alienWare">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/handpicked/alienware.jpg`}
										alt="AliwenWare"
									/>
								</NavLink>
							</div>
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/beatsHeadphones">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/handpicked/beats.jpg`}
										alt="Beats"
									/>
								</NavLink>
							</div>
						</div>
					</div>
					<div className="category-container c-deals">
						<h4>Latest Deals</h4>
						<p className="banner-subtitle">The best of our deals</p>
						<div className="row">
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/flashsale">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/deals/flash-Sale.jpg`}
										alt="Flash Sale"
									/>
								</NavLink>
							</div>
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/50-discount">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/deals/50-Off-Discount.jpg`}
										alt="50% off discount"
									/>
								</NavLink>
							</div>
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/30-discount">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/deals/Mobile-Sale.jpg`}
										alt="Mobile Sale"
									/>
								</NavLink>
							</div>
						</div>
					</div>
					<div className="category-container">
						<h4>Categories</h4>
						<p className="banner-subtitle">Top most categories</p>
						<div className="row">
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/electronics">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/handpicked/electronics.jpg`}
										alt="Nikon"
									/>
								</NavLink>
							</div>
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/sports">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/handpicked/sports2.jpg`}
										alt="AliwenWare"
									/>
								</NavLink>
							</div>
							<div className="col-xs-12 col-md-4 pb-3 category-card">
								<NavLink to="/search/stationaries">
									<img
										className="d-block w-100 h-100"
										src={`${ASSETS_PUBLIC_PATH}/images/handpicked/stationary3.jpg`}
										alt="Beats"
									/>
								</NavLink>
							</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

// HomePage.propTypes = {
// 	Carouselobj: PropTypes.object.isRequired
// };
