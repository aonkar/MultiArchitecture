import {dude, userName} from './file1';
dude.file1Func();
console.log('userName Value : ', userName);

const file2 = require('./file2');
file2.file2Func();


// document.getElementById('loadFile').addEventListener('click', function() {
// 	const p = System.import('./file2');

// 	p.then(function(obj) {
// 		console.log('File Loaded : ', obj);
// 		obj.file2Func();
// 	});

// 	const p2 = System.import('./file1');

// 	p2.then(function(obj) {
// 		console.log('File Loaded : ', obj);
// 		obj.dude.file1Func();
// 	});
// });
