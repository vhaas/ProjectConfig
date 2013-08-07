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
	systems : DS.hasMany("App.System"),
	roadMaps : DS.hasMany("App.RoadMap"),
	href : (function() {
		return "#" + this.get('id');
	}).property('id').cacheable()
});

App.UserStory = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	goal : DS.attr("string"),
	benefit : DS.attr("string"),
	epic : DS.belongsTo("App.Epic"),
	role : DS.belongsTo("App.Role"),
	project : DS.belongsTo("App.Project"),
	mileStones : DS.hasMany("App.MileStone"),
	systemChanges : DS.hasMany("App.SystemChange")
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
	firstEffortEstimates : DS.hasMany("App.FirstEffortEstimate"),
	userStories : DS.hasMany("App.UserStory")
});

App.FirstEffortEstimate = DS.Model.extend({
	effortType : DS.attr("string"),
	minEffort : DS.attr("string"),
	medEffort : DS.attr("string"),
	maxEffort : DS.attr("string"),
	risk : DS.attr("string"),
	project : DS.belongsTo("App.Project"),
	systemChanges : DS.belongsTo("App.SystemChange"),
	effortRole : DS.belongsTo("App.EffortRole")
});

App.AdaptionType = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	systemChanges : DS.hasMany("App.SystemChange"),
	project : DS.belongsTo("App.Project")
});

App.EffortRole = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	dailyRate : DS.attr("string"),
	firstEffortEstimates : DS.hasMany("App.FirstEffortEstimate")	
});
