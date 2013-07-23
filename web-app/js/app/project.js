App.ProjectIndexRoute = Ember.Route.extend({
	setupController : function(controller, model) {
//		alert('Project model: ' + model);		
		var projectId = model.get('id');
//		alert('Project ID: ' + model);
		this.controllerFor('roadmaplist').set('model', App.RoadMap.find({project : projectId}));
	},
	model : function(params) {
//		alert('Project params:' + params);
		return App.Project.find(params.project_id);
	},
	renderTemplate : function() {
		this.render('project', {
			into : 'application'
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

App.RoadmapListView = Ember.View.extend({

});

App.ProjectIndexView = Ember.View.extend({
	templateName : 'project',
	parentView : 'projects'
});