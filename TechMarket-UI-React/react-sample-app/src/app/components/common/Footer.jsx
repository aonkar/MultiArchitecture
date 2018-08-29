import React from 'react';
import { ShareButtons, generateShareIcon } from 'react-share';
// Styles
import 'stylesheets/components/common/Footer';

export default class Footer extends React.Component {
	render() {
		const { FacebookShareButton, TwitterShareButton } = ShareButtons;
		const FacebookIcon = generateShareIcon('facebook');
		const TwitterIcon = generateShareIcon('twitter');
		const shareUrl = 'https://www.deloitte-kart.com';
		const title = 'Deloitte E-Kart';

		return (
			<footer className="footer">
				<div className="container row">
					<ul>
						<li>About Us</li>
						<li>Contact Us</li>
						<li>Customer Care</li>
					</ul>
					<div>
						<FacebookShareButton
							url={shareUrl}
							quote={title}
							hashtag="#yoyoyo"
							className="social-icon"
						>
							<FacebookIcon size={40} round />
						</FacebookShareButton>
						<TwitterShareButton
							url={shareUrl}
							title={title}
							className="social-icon"
						>
							<TwitterIcon size={40} round />
						</TwitterShareButton>
					</div>
				</div>
			</footer>
		);
	}
}
