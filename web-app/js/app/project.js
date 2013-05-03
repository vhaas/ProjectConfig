App.ProjectRoute = Ember.Route.extend({	
	events: {
		selectProject: function(id) {
//			var epics = App.Epic.find({project_id: "id"});
//			var epics = App.Epic.find({ project_id: "id" });
			var epics = this.controller.get('content').get('epics').get('firstObject');			
			this.transitionTo('epic', epics);
		}
	}		
});

//Controller
App.ProjectController = Ember.ObjectController.extend({
	save: function(){
		var model = App.store.commit();
	},
	createProject: function() {
		var project = App.Project.createRecord();
		return project;
	},
	disabledProject: true
});

//View
App.ProjectView = Ember.View.extend({
	createNewProject: function() {
		this.get('controller').set('content', this.get('controller').createProject());
	},
	isNotDirty: function() {
		return !this.get('controller.content.isDirty') 
	}.property('controller.content.isDirty').cacheable(),	
	enableProject: function() {
		this.get('controller').toggleProperty('disabledProject');
	}
});