//Route
App.RoadMapRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		controller.set('content', model);
		var project = controller.get('content').get('project').get('id');
		controller.set('unSelectedUserStories', this.getUserStories(project));
	},
	model: function() {
	    var controller;
	    controller = this.controllerFor('RoadMap');
	    return controller.get('content');
	  },
	getUserStories : function(id) {
		var userStories = App.UserStory.find({
			project : id
		});
		userStories.one("didLoad", function() {
			userStories.resolve(userStories.get("firstObject"));
		});
		return userStories;
	}
});

App.RoadMapController = Ember.ObjectController.extend({
	needs: ['userstorys', 'milestones'],
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