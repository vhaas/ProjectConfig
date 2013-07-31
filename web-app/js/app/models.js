App.Epic = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	project : DS.belongsTo("App.Project"),
	userStories : DS.hasMany("App.UserStory"),
	href : (function() {
		return "#" + this.get('id');
	}).property('id').cacheable()
});

App.Project = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	epics : DS.hasMany("App.Epic"),
	href : (function() {
		return "#" + this.get('id');
	}).property('id').cacheable()
});

App.UserStory = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	goal : DS.attr("string"),
	benefit : DS.attr("string"),
//	roadMap : DS.belongsTo("App.RoadMap"),
	epic : DS.belongsTo("App.Epic"),
	role : DS.belongsTo("App.Role"),
	project : DS.belongsTo("App.Project"),
	mileStones : DS.hasMany("App.MileStone")	
//	mileStoneUserStories : DS.hasMany("App.MileStoneUserStory"),
//	mileStones: (function() {
//		return this.get('mileStoneUserStories').getEach('mileStone');
//	}).property('mileStoneUserStories.@each.relationshipsLoaded')
});

App.Role = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	project : DS.belongsTo("App.Project")
});

App.RoadMap = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	project : DS.belongsTo("App.Project"),
	mileStones : DS.hasMany("App.MileStone"),
	href : (function() {
		return "#" + this.get('id');
	}).property('id').cacheable()
});

App.MileStone = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	orderId : DS.attr("number"),
	roadMap : DS.belongsTo("App.RoadMap"),
	userStories : DS.hasMany("App.UserStory"),
	project : DS.belongsTo("App.Project"),
	nameIsEmpty : (function() {
		return Ember.isEmpty(this.get('name'));
	}).property('name')
//	mileStoneUserStories : DS.hasMany("App.MileStoneUserStory"),
//	userStories: (function() {
//		return this.get('mileStoneUserStories').getEach('userStory');
//	}).property('mileStoneUserStories.@each.relationshipsLoaded')
});

//App.MileStoneUserStory = DS.Model.extend({
//	mileStone : DS.belongsTo("App.MileStone"),
//	userStory : DS.belongsTo("App.UserStory"),
//	relationshipsLoaded : (function(){
//		return this.get('mileStone.isLoaded') && this.get('userStory.isLoaded');
//	}).property('mileStone.isLoaded', 'userStory.isLoaded')
//});