App.ProjectsRoute = Ember.Route.extend({
	setupController: function(controller) {
		controller.set('content', App.Project.find());
	}
});

App.ProjectsController = Ember.ArrayController.extend({
	  itemController: 'project'
});