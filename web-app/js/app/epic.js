//Route
App.EpicRoute = Ember.Route.extend({
	setupController: function(controller, model) {		
		controller.set('content', model);
		var projectId = this.controller.get('content').get('project').get('id');
		controller.set('epics', this.getEpics(projectId));
	},
	events: {
		openModal: function(role) {
			var roleController = this.controllerFor('role');
			roleController.set('content', role);
			var modalView = App.ModalView.create({				
				controller: roleController
			});
			modalView.append();
		},
		switchToProject: function() {
			this.transitionTo('projects');
		}
	},
	getEpics: function(id) {
		var epics = App.Epic.find({ project: id });
		epics.one("didLoad", function() {
			epics.resolve(epics.get("firstObject"));
		});
		return epics;
	}
});

//Controller
App.EpicController = Ember.ObjectController.extend({
	save: function(){		
		var model = App.store.commit();
	},
	createUserStory: function() {
		var userStory = App.UserStory.createRecord();
		userStory.set('epic', this.content);
		return userStory;
	},
	createEpic: function() {		
		var epic = App.Epic.createRecord();
		epic.set('project', this.content.project);
		return epic;
	},
	deleteUserStory: function(id) {
		var model = App.UserStory.find(id);
		model.deleteRecord();
	    App.store.commit();
	},
	disabledEpic: true,
	disabledUserStory: true,
	epics: null
});

//View
App.EpicView = Ember.View.extend({
	createNewEpic: function() {
		this.get('controller').set('content', this.get('controller').createEpic());
	},
	isNotDirty: function() { 
		return !this.get('controller.content.isDirty') 
	}.property('controller.content.isDirty').cacheable(),
	saveAndCreate: function() {
		this.get('controller').save();
		this.get('controller').createUserStory();
	},
	enableEpic: function() {
		this.get('controller').toggleProperty('disabledEpic');
		this.get('controller').set('disabledUserStory', true);
	},
	enableUserStory: function() {
		this.get('controller').toggleProperty('disabledUserStory');
		this.get('controller').set('disabledEpic', true);
	}
});