App.RoadmapRoute = Ember.Route.extend({
	setupController: function(controller, model) {
		this.controller.set('content', model);
//		var projectId = this.modelFor('project').get('id');
		var projectId = model.get('project').get('id');
		var roadmapId = model.get('id');
		var mileStones = App.MileStone.find({roadmap:roadmapId});
		var userStories = App.UserStory.find({project:projectId});
		var filteredUserStories = App.UserStory.filter((
				function(userStory) {
				if (userStory) {
					if (userStory.get('project')) {
						if (userStory.get('project').get('id') === projectId) {
							var mileStones = userStory.get('mileStones');
							var boolean = true;
							mileStones.forEach(function(mileStone) {
								if (mileStone.get('roadMap')) {
									if (mileStone.get('roadMap').get('id') === roadmapId) {
										boolean = false;
									}
								}
							});
							return boolean;
						}
					}
				}
				return false;
			}));		
		this.controllerFor('userstorylist').set('content', filteredUserStories);
		this.controllerFor('milestones').set('content', mileStones);
	},
	model : function(params) {
		alert('Params: ' + params.roadmap_id);
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
	selection : null,
	active : true
});

App.MilestonesController = Ember.ArrayController.extend({

});

App.SelectController = Ember.ArrayController.extend({
	selection : null,
	active : true
});

App.Select = Ember.Select.extend({
	multiple : true,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id'
});

//App.SelectuserstoryController = App.SelectController.create();
//App.SelectuserstoryController.set('content', App.UserStory.find());
	