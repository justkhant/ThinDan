var mongoose = require("mongoose");

mongoose.connect("mongodb+srv://khantk:thindan@cluster0-vregg.mongodb.net/test?retryWrites=true&w=majority");
// above: running mongoose on Atlas

var Schema = mongoose.Schema;

var tutorSchema = new Schema({
	username: {type: String, required: true, unique: true},
	password: {type: String},
	name: {type: String}
});

// export userSchema as a class called User
module.exports = mongoose.model('Tutor', tutorSchema);

tutorSchema.methods.standardizeName = function() {
    this.email = this.email.toLowerCase();
    return this.email;
}