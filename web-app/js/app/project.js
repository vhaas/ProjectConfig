App.ProjectIndexRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		var projectId = model.get('id');
		this.controllerFor('roadmaplist').set('model', App.RoadMap.find({project : projectId}));
	},
	model : function(params) {
		this._super();
		return App.Project.find(params.project_id);
	},
	renderTemplate : function() {
		this.render('roadmap-list', {
			into : 'project',
			outlet : 'roadmap-list',
			controller : 'roadmaplist'
		});
	},
	events : {
		select : function(id) {
			// alert('RoadMap ID: ' +
			// this.get('controller').get('controllers.roadmaplist').get('content').get('firstObject'));
			var roadmap = App.RoadMap.find(id);
			this.transitionTo('roadmap', roadmap);
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
	parentView : 'projects'
});