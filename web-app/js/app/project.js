App.ProjectIndexRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		var projectId = this.modelFor('project').get('id');
		this.controllerFor('roadmaplist').set('content', App.RoadMap.find({project : projectId}));
	},
	model : function(params) {
		return App.Project.find(params.project_id);
	},
	renderTemplate : function() {
		this.render('project', {
			into : 'application',
			controller : 'project'
		}),
		this.render('roadmap-list', {
			into : 'project',
			outlet : 'roadmap-list',
			controller : 'roadmaplist'
		});
	},
	events : {
		select : function(id) {
			var roadmap = App.RoadMap.find(id);
			this.transitionTo('roadmap', roadmap);
		},
		error: function(error, transition) {
			alert('ERROR: ' + error);
		}
	}
});

App.ProjectIndexController = Ember.ObjectController.extend({
	needs : 'roadmaplist'
});

App.RoadmaplistController = Ember.ArrayController.extend({});

App.RoadmaplistView = Ember.View.extend({
    sortProperties: ['name'],
    sortAscending: true
});

App.ProjectIndexView = Ember.View.extend({
	templateName : 'project',
	parentView : 'projects'
});