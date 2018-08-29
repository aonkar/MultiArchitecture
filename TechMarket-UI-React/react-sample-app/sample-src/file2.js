console.log('File 2 Loaded');

function file2Func() {
	console.log('file2Func fn() executed');
}

const companyName = 'Deloitte';

module.exports = {
	file2Func: file2Func,
	companyName: companyName
};
