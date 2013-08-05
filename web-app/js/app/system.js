App.SystemRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		this.controller.set('content', model);
	}

});