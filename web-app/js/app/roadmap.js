App.RoadmapRoute = Ember.Route.extend({
	model: function(params) {
	    return App.RoadMap.find(params.roadmap_id);
	}
});

// Controller


//App.RoadmapsController = Ember.ArrayController.extend({
//});

