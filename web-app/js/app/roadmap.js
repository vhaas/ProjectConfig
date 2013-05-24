//Route
App.RoadmapRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		controller.set('content', model);
		var project = controller.get('content').get('project').get('id');
		controller.set('unSelectedUserStories', this.getUserStories(project));
	},
	model: function() {
	    var controller;
	    controller = this.controllerFor('roadmap');
	    return controller.get('content');
	  },
	getUserStories : function(id) {
		var userStories = App.UserStory.find({
			project : id
		});
		userStories.one("isLoaded", function() {
			userStories.resolve(userStories.get("firstObject"));
		});
		return userStories;
	}
});

App.RoadmapController = Ember.ObjectController.extend({
	needs: ['userstorys'],
	save : function() {
		var model = App.store.commit();
	},
	createRoadMap : function() {
		var roadMap = App.RoadMap.createRecord();
		roadMap.set('project', this.content.get('project').get('id'));
		return roadMap;
	},
	selectedUserStories : null,
	unSelectedUserStories : null
});

App.UserstorysController = Ember.ArrayController.extend({});

// App.SelectUserStoryController = App.SelectController.create();
// App.SelectUserStoryController.set('content',
// App.RoadMapController.get('unSelectedUserStories'));

// View
App.RoadMapView = Ember.View.extend({
	createRoadMapEpic : function() {
		this.get('controller').set('content',
				this.get('controller').createRoadMap());
	},
	isNotDirty : function() {
		return !this.get('controller.content.isDirty')
	}.property('controller.content.isDirty').cacheable(),
	saveAndCreate : function() {
		this.get('controller').save();
		this.get('controller').createUserStory();
	}
//	enableEpic : function() {
//		this.get('controller').toggleProperty('disabledEpic');
//		this.get('controller').set('disabledUserStory', true);
//	},
//	enableUserStory : function() {
//		this.get('controller').toggleProperty('disabledUserStory');
//		this.get('controller').set('disabledEpic', true);
//	}
});