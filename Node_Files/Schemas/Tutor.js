var mongoose = require("mongoose");

mongoose.connect("mongodb+srv://khantk:thindan@cluster0-vregg.mongodb.net/test?retryWrites=true&w=majority");
// above: running mongoose on Atlas

var Schema = mongoose.Schema;

var tutorSchema = new Schema({
	facebook_id: {type: String, required: true, unique: true},
	name: {type: String, required: true}
});

// export userSchema as a class called User
module.exports = mongoose.model('Tutor', tutorSchema);

tutorSchema.methods.standardizeName = function() {
    this.email = this.email.toLowerCase();
    return this.email;
}