// Connect to MongoDB Atlas cluster
const mongoose = require('mongoose');
const connector = mongoose.connect("mongodb+srv://khantk:thindan@cluster0-vregg.mongodb.net/test?retryWrites=true&w=majority", { useNewUrlParser: true });

// set up Express
var express = require('express');
var app = express();

//app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// set up BodyParser
var bodyParser = require('body-parser');
var urlencodedParser = bodyParser.urlencoded({ extended: false })

app.use(bodyParser.json({ limit: "500mb" }));
app.use(bodyParser.urlencoded({ extended: true }));


// import the Tutor class from Tutor.js
var Tutor = require('./Schemas/Tutor.js');


/************************ USER STUFF ***************************/

// route for creating a new User
// this is the action of the SignUp button on the SignUp Page
app.use('/createNewTutor', (req, res) => {
	// construct the User from the form data which is in the request body
	var newTutor = new Tutor({
		facebook_id: "1",
		name: "Test_User"
	});

	console.log("Creating new Tutor...");
	console.log("Facebook_id: " + newTutor.facebook_id);
	console.log("Name: " + newTutor.name);

	// save the user to the database
	newTutor.save((err) => {
		if (err) {
			res.type('html').status(200);
			res.write('uh oh: ' + err);
			console.log(err);
			return res.status(200).json({
				message: "Error creating user"
			});
		}
		else {

			console.log("New User Created Successfully...")
			return res.status(200).json({
				message: "User Created Successfully"
			});
		}
	});
});


// route for showing all the users
app.use('/all', (req, res) => {
	/*
	console.log("Show all users");

	// find all the User objects in the database
	User.find({}, (err, users) => {
		if (err) {
			res.type('html').status(200);
			console.log('uh oh' + err);
			res.write(err);
		}
		else {
			if (users.length == 0) {
				res.type('html').status(200);
				res.write('There are no users.');
				res.end();
				return;
			}
			// use EJS to show all the users
			res.render('all', { users: users });

		}
	}).sort({ 'email': 'asc' }); // this sorts them BEFORE rendering the results

});

// route for accessing data via the web api
// to use this, make a request for /api to get an array of all User objects
// or /api?username=[whatever] to get a single object
app.use('/search_user', (req, res) => {
	console.log("Searching for User:" + req.query.email + "...");

	// construct the query object
	var queryObject = {};
	if (req.query.email) {
		// if there's a email in the query parameter, use it here
		queryObject = { "email": req.query.email };
	}

	User.find(queryObject, (err, users) => {
		if (err) {
			console.log('uh oh' + err);
			return res.json({});
		}
		else if (users.length == 0) {
			// no objects found, so send back empty json
			return res.json({});
		}

		else if (users.length > 0) {
			var user = users[0];
			var temp = {
				"email": user.email, "password": user.password, "name": user.name, "school": user.school, "bio": user.bio,
				"rank": user.rank, "points": user.points, "phoneNumber": user.phoneNumber
			};
			console.log(temp);

			// send back a single JSON object
			return res.json({
				"email": user.email, "password": user.password, "name": user.name, "school": user.school, "bio": user.bio,
				"rank": user.rank, "points": user.points, "phoneNumber": user.phoneNumber, "profilePic": user.profilePic
			});

		}

	});
	*/
});



/*************************************************/
app.use('/public', express.static('public'));

app.listen(3000, () => {
	console.log('Listening on port 3000');
});
