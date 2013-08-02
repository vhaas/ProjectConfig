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
});

App.System = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	systemChanges : DS.hasMany("App.SystemChange"),
	project : DS.belongsTo("App.Project"),
	href : (function() {
		return "#" + this.get('id');
	}).property('id').cacheable()
});

App.SystemChange = DS.Model.extend({	
	adaptionAspect : DS.attr("string"),
	project : DS.belongsTo("App.Project"),
	system : DS.belongsTo("App.System"),
	adaptionType : DS.belongsTo("App.AdaptionType"),
	firstEffortEstimate : DS.belongsTo("App.FirstEffortEstimate"),
	userStories : DS.hasMany("App.UserStory")
});

App.FirstEffortEstimate = DS.Model.extend({
	effortType = DS.attr("string"),
	minEffort = DS.attr("string"),
	medEffort = DS.attr("string"),
	maxEffort = DS.attr("string"),
	risk = DS.attr("string"),
	project : DS.belongsTo("App.Project"),
	systemChanges : DS.hasMany("App.SystemChange")
});

App.AdaptionType = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	systemChanges : DS.hasMany("App.SystemChange"),
	project : DS.belongsTo("App.Project")
});
