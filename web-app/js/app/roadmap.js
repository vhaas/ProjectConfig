App.RoadmapRoute = Ember.Route.extend({
	setupController: function(controller, model) {
		var projectId = model.get('project').get('id');
		var roadmapId = model.get('id');
		alert('Roadmap Id: ' + roadmapId);
		var mileStones = App.MileStone.find({roadmap:roadmapId});
		var userStories = App.UserStory.find({project:projectId});
		var filteredUserStories = App.UserStory.filter((
				function(userStory) {
				if (userStory) {
					var mileStones = userStory.get('mileStones');
					var boolean = true;
					mileStones.forEach(function(mileStone) {
						boolean = false;
					});
					return boolean;
				}
				return false;
			}));		
		this.controllerFor('userstorylist').set('model', filteredUserStories);
		this.controllerFor('milestones').set('model', mileStones);
	},
	getUserStories: function(projectId) {
		var userStories = App.UserStory.find({project:projectId});
		return userStories;
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
			var project = App.Project.find(id);+
			alert(project);
			this.transitionTo('project', project);
		},
		showContent : function() {
			alert('Content: ' + this.get('controller'));
		}
	}
});

// Controller
App.RoadmapsController = Ember.ArrayController.extend({	
//	needs : ['userstorylist', 'milestones']
});

App.RoadmapIndexView = Ember.View.extend({
//	templateName : 'roadmap',
	parentView : 'application'
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
	