App.RoadmapRoute = Ember.Route.extend({
	setupController: function(controller, model) {
		var projectId = model.get('project').get('id');
		this.controllerFor('userstorylist').set('model', App.UserStory.find({project:projectId}));
//		this.controllerFor('selectuserstories', App.UserStory).set('content', App.UserStory.find({project:projectId}));
		var roadmapId = model.get('id');
		this.controllerFor('milestones').set('model', App.MileStone.find({roadmap:roadmapId}));
	},
	model: function(params) {
		this._super();
	    return App.RoadMap.find(params.roadmap_id);
	},
	renderTemplate : function() {
		this.render('roadmap', {
			into : 'application'
		}),
		this.render('unselected-userstories', {
			into : 'roadmap',
			outlet : 'unselected-user-stories',
			controller : 'userstorylist'
		}),
		this.render('milestone', {
			into : 'roadmap',
			outlet : 'mileStones',
			controller : 'milestones'
		})
	},
	events : {
		select : function(id) {
			alert("UserStory " + id + " was selected");
		},
		goBack : function(id) {
			var project = App.Project.find(id);
			this.transitionTo('project', project);
		}
	}
});

// Controller
App.RoadmapsController = Ember.ArrayController.extend({	
//	needs : ['userstorylist', 'milestones']
});

App.RoadmapView = Ember.View.extend({
	templateName : 'roadmap',
	parentView : 'project.index'
});

App.UserstorylistController = Ember.ArrayController.extend({
	selection : null
});

App.MilestonesController = Ember.ArrayController.extend({

});

App.SelectController = Ember.ArrayController.extend({
	selection : null
});

App.SelectUserStory = Ember.Select.extend({
	contentBinding : "App.UserstorylistController.content",
	selectionBinding : "content.selection",
    optionLabelPath : "content.name",
    optionValuePath : "content.id",
    prompt : "Please select a userStory"
});