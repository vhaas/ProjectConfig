App.RoadmapRoute = Ember.Route.extend({		
	setupController: function (controller, model){
	    console.log('sc', model);
	    this._super(this, arguments);
	}
});

// Controller


App.RoadmapsController = Ember.ArrayController.extend({
});

