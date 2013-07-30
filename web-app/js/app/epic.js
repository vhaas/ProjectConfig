//Route
App.EpicRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		controller.set('content', model);		
		var projectId = model.get('project').get('id');
		var epics = App.Epic.find({project:projectId});
		var roles = App.Role.find({project:projectId});
		this.controllerFor('epics').set('content', epics);
		this.controllerFor('roles').set('content', roles);
		this.controllerFor('userstories').set('content', model.get('userStories'));
	},
	model : function(params) {
		return App.Epic.find(params.epic_id);
	},
	renderTemplate : function() {
		this.render('epic', {
			into : 'application',
			controller : 'epic'
		}),
		this.render('userstory-list', {
			into : 'epic',
			outlet : 'userStories',
			controller : 'userstories'
		}),
		this.render('epics', {
			into : 'epic',
			outlet : 'epic-accordion',
			controller : 'epics'
		})
	},
	events : {		
		switchToProject : function() {
			this.transitionTo('projects');
		},
		editRole: function(role) {
	        this.controllerFor('role.modal').edit(role);
	        this.send('openModal', 'role.modal');
	    },
	    createRole: function(userStory) {
	        this.controllerFor('role.modal').create(userStory);
	        this.send('openModal', 'role.modal');
	    },
	    controllercheck : function() {
			alert('RolesControllor Content: ' + App.RolesController.content);
		}
	}
});

// Controller
App.EpicsController = Ember.ArrayController.extend({
	itemController : 'epic',
	select : function(epic) {
		this.transitionToRoute('epic', epic);
	}
});

App.EpicController = Ember.ObjectController.extend({
	save : function() {
		var model = App.store.commit();
	},
	createUserStory : function() {
		var userStory = App.UserStory.createRecord();
		userStory.set('epic', this.content);
		this.set('disabledEpic', true);
		this.set('disabledUserStory', false);
		return userStory;
	},
	createEpic : function() {
		var epic = App.Epic.createRecord();
		epic.set('project', this.content.get('project'));
		this.set('disabledEpic', false);
		return epic;
	},
	deleteUserStory : function(id) {
		var model = App.UserStory.find(id);
		model.deleteRecord();
		App.store.commit();
	},
	disabledEpic : true,
	disabledUserStory : true,
	epics : null
});

App.UserstoriesController = Ember.ArrayController.extend({
	itemController : 'userstory'
});

App.UserstoryController = Ember.ObjectController.extend({
	needs : ['roles']
});

// View
App.EpicView = Ember.View.extend({
	createNewEpic : function() {
		this.get('controller').set('content',
				this.get('controller').createEpic());
	},
	isNotDirty : function() {
		return !this.get('controller.content.isDirty')
	}.property('controller.content.isDirty').cacheable(),
	saveAndCreate : function() {
		this.get('controller').save();
		this.get('controller').createUserStory();
	},
	enableEpic : function() {
		this.get('controller').toggleProperty('disabledEpic');
		this.get('controller').set('disabledUserStory', true);
	},
	enableUserStory : function() {
		this.get('controller').toggleProperty('disabledUserStory');
		this.get('controller').set('disabledEpic', true);
	}
});

App.EpicsView = Ember.View.extend({
	templateName : 'epic-accordion'
});

App.EpicAccordionView = Ember.View.extend({
	templateName : 'epic-acc-item',
	classNames : [ 'accordion-group' ],
	didInsertElement : function() {
		return Ember.run.next(this, function() {
			return this.$('.collapse').collapse({
				parent : "#accordion2"
			});
		});
	}
});