App.ProjectIndexRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		var projectId = this.modelFor('project').get('id');
		this.controllerFor('roadmap.list').set('content', App.RoadMap.find({project : projectId}));
		this.controllerFor('epic.list').set('content', App.Epic.find({project : projectId}));
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
			controller : 'roadmap.list'
		}),
		this.render('epic-list', {
			into : 'project',
			outlet : 'epic-list',
			controller : 'epic.list'
		});
	},
	events : {
		selectRoadmap : function(id) {
			var roadmap = App.RoadMap.find(id);
			this.transitionTo('roadmap', roadmap);
		},
		selectEpic : function(id) {
			var epic = App.Epic.find(id);
			this.transitionTo('epic', epic);
		},
		error: function(error, transition) {
			alert('ERROR: ' + error);
		}
	}
});

App.ProjectIndexController = Ember.ObjectController.extend({
	needs : ['roadmap.list', 'epic.list']
});

App.RoadmapListController = Ember.ArrayController.extend({
	sortProperties: ['name'],
    sortAscending: true
});

App.EpicListController = Ember.ArrayController.extend({
	sortProperties: ['name'],
    sortAscending: true
});

App.RoadmapListView = Ember.View.extend({
	templateName : 'roadmap-list'
});

App.EpicListView = Ember.View.extend({
	templateName : 'epic-list'
});

App.ProjectIndexView = Ember.View.extend({
	templateName : 'project',
	parentView : 'projects'
});