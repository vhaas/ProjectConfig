App.ProjectsRoute = Ember.Route.extend({
	setupController: function(controller) {
		controller.set('content', App.Project.find());
	},
	events: {
		select: function(id) {			
			var project = App.Project.find(id);
			this.transitionTo('project', project);
		}
	}
});

App.ProjectsController = Ember.ArrayController.extend({
	  itemController: 'project'
});

App.ProjectsView = Ember.View.extend({
	expanded: true,
	expand: function() {
		this.toggleProperty('expanded');
	}
});