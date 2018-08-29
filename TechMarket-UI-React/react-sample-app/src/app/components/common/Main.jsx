import React from 'react';
import { Route, Redirect, Switch } from 'react-router-dom';

// import HomePage from 'components/pages/HomePage';
// import SearchPage from 'components/pages/SearchPage';
// import ProductDetailsPage from 'components/pages/ProductDetailsPage';
// import CartPage from 'components/pages/CartPage';
// import OrderPage from 'components/pages/OrderPage';
import LoginPage from 'components/pages/login/LoginPage';
import UsersPage from 'components/pages/users/UsersPage';
import PageNotFound from 'components/pages/pageNotFound/PageNotFound';
// import pageWrapper from 'components/common/pageWrapper/PageWrapper';

// import ScrollToTop from 'components/common/ScrollToTop';

// Styles
import 'stylesheets/components/common/Main.scss';

export default class Main extends React.Component {
	render() {
		return (
			<main>
				<Switch>
					<Route path="/" exact component={LoginPage} />
					<Route path="/signIn" exact component={LoginPage} someValue="MyValus is good" />
					<Route path="/users" component={UsersPage} />
					{/* <Route
						path="/users"
						exact
						component={pageWrapper(UsersPage, { isPublic: true })}
					/> */}
					<Route path="/page-not-found" exact component={PageNotFound} />
					<Redirect to="/page-not-found" />
				</Switch>

				{/* <Route path="/home" component={HomePage} /> */}
				{/* <ScrollToTop> */}
				{/* <Route path="/search/:searchKeyword" component={SearchPage} /> */}
				{/* </ScrollToTop> */}
				{/* <Route path="/pdp/:productId" component={ProductDetailsPage} />
				<Route path="/cart" component={CartPage} />
				<Route path="/orders/:orderId" component={OrderPage} /> */}
			</main>
		);
	}
}
