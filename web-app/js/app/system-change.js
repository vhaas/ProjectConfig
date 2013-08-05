App.SystemchangeRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		this.controller.set('content', model);
		this.controllerFor('user.stories.table').set('selected', model);
		console.log('Project: ' + model.get('project').get('id'));
		var projectId = model.get('project').get('id');
		console.log('Project: ' + model);
		this.controllerFor('user.stories.table').set('content', App.UserStory.find({project:projectId}));
		this.controllerFor('system.changes').set('content', model.get('systemChanges'));
	},
	model : function(params) {
		return App.UserStory.find(params.user_story_id);
	},
	renderTemplate : function() {
		this.render('system.change.input', {
			into : 'application',
			controller : 'user.story.table'
//		}),
//		this.render('userstory-table', {
//			into : 'system.change',
//			outlet : 'userstory-table',
//			controller : 'user.stories.table'
		})
	},
	events : {
		editRole: function(role) {
	        this.controllerFor('role.modal').edit(role);
	        this.send('openModal', 'role.modal');
	    }
	}
});

App.SystemChangeInputView = Ember.View.extend({
	templateName : 'system-change-input'
})

App.UserStoriesTableController = Ember.ArrayController.extend({
	itemController : 'user.story.table',
	selected : null
});

App.UserStoryTableController = Ember.ObjectController.extend({
	
});

App.SystemChangesController = Ember.ArrayController.extend({
	itemController : 'system.change'
});

App.SystemChangeController = Ember.ObjectController.extend({
	
});

App.SystemsController = Ember.ArrayController.extend({
	needs : ['system.changes']
});