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
	roadMap : DS.belongsTo("App.RoadMap"),
	epic : DS.belongsTo("App.Epic"),
	role : DS.belongsTo("App.Role"),
	willGetDeleted : DS.attr("boolean", {
		defaultValue : false
	})
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
	mileStones : DS.hasMany("App.MileStone")
});

App.MileStone = DS.Model.extend({
	name : DS.attr("string"),
	description : DS.attr("string"),
	oderId : DS.attr("integer"),
	roadMap : DS.belongsTo("App.RoadMap"),
	userStories : DS.hasMany("App.UserStory")
});