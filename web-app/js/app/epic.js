//Route
App.EpicRoute = Ember.Route.extend({
	setupController: function(controller) {
		var epics = App.Epic.find({project_id:p_id});
		alert("Länge: " + epics.length)
		if (!epics) {
			this.transitionTo('projects');
		} else
			controller.set('epics', epics);
			controller.set('content', epics.indexOf(0));
		}
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
			var project = App.Project.find(1);			
//			this.transitionTo('project', project);
			this.transitionTo('projects');
		}
	}
});

//Controller
App.EpicController = Ember.ObjectController.extend({
	save: function(){		
		var model = App.store.commit();
	},
	epics: App.Epic.find(),	
	createUserStory: function() {
		var userStory = App.UserStory.createRecord();
		userStory.set('epic', this.content);
		return userStory;
	},
	createEpic: function() {		
		var epic = App.Epic.createRecord();
		epic.set('project', 1);
		return epic;
	},
	deleteUserStory: function(id) {
		var model = App.UserStory.find(id);
		model.deleteRecord();
	    App.store.commit();
	},
	disabledEpic: true,
	disabledUserStory: true
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