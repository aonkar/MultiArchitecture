// jQuery Import - Already done using Provider Plug-in
// Popper Import - Already done using Provider Plug-in

// Bootstrap 4
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min.js';

// Font Awesome
import 'font-awesome/css/font-awesome.css';

// Global Application Styling
import 'stylesheets/global.scss';

// react-bootstrap-table
import 'react-bootstrap-table/css/react-bootstrap-table.css';

// toastr - CSS depedency
import 'toastr/build/toastr.css';
import 'stylesheets/override/toastr.scss';

// toastr - configuration
toastr.options = {
	closeButton: false,
	debug: false,
	newestOnTop: false,
	progressBar: false,
	positionClass: 'toast-bottom-right',
	preventDuplicates: false,
	onclick: null,
	showDuration: '300',
	hideDuration: '1000',
	timeOut: '5000',
	extendedTimeOut: '1000',
	showEasing: 'swing',
	hideEasing: 'linear',
	showMethod: 'fadeIn',
	hideMethod: 'fadeOut'
};
