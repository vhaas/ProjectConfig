App.SystemchangeRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		this.controllerFor('user.story.table').set('content', model);
		this.controllerFor('user.stories.table').set('selected', model);
		var projectId = model.get('project').get('id');
		this.controllerFor('user.stories.table').set('content', App.UserStory.find({project:projectId}));
		this.controllerFor('systems.table').set('content', App.System.find({project:projectId}));
		this.controllerFor('system.changes').set('content', model.get('systemChanges'));
		this.controllerFor('filter.system').set('content', App.System.find({project:projectId}));
//		this.controllerFor('system.changes').set('content', model.get('systemChanges'));
	},
	model : function(params) {
		return App.UserStory.find(params.user_story_id);
	},
	renderTemplate : function() {
		this.render('system-change-input', {
			into : 'application',
			controller : 'user.story.table'
		}),
		this.render('userstory-table', {
			into : 'system-change-input',
			outlet : 'userstory-table',
			controller : 'user.stories.table'
		}),
		this.render('system-table', {
			into : 'system-change-input',
			outlet : 'system-table',
			controller : 'systems.table'
		})
	},
	events : {
		editRole: function(role) {
	        this.controllerFor('role.modal').edit(role);
	        this.send('openModal', 'role.modal');
	    },
	    addSystemChange : function(userStory) {
	    	this.controllerFor('system.changes.modal').create(userStory);
	    	this.send('openModal', 'system.change.modal');
	    },
	    doda : function(item) {
	    	console.log('SystemChanges: ' + item.get('systemChanges'));
	    }
	}
});

App.SystemChangeInputView = Ember.View.extend({
	templateName : 'system-change-input'
});

App.UserstoryTableView = Ember.View.extend({
	templateName : 'userstory-table'
});

App.UserStoriesTableController = Ember.ArrayController.extend({
	itemController : 'user.story.table',
	needs : ['system.changes'],
	sortProperties: ['name'],
    sortAscending: true,
	selected : null
});

App.UserStoryTableController = Ember.ObjectController.extend({
	
});

App.SystemsTableController = Ember.ArrayController.extend({
	needs : ['userStoryTable'],
	filteredContent : (function() {
		var content = this.get('content'), userstory = this.get('controllers')
				.get('userStoryTable').get('content');
		if (!content) {
			return content;
		}
		return content.filter(function(item) {
			var isAssigned = true;
			userstory.get('systemChanges').forEach(function(systemchange) {
				if (!systemchange) {
					return;
				} else {					
					var system = systemchange.get('system');
					if (!system) {
						return;
					} else {
						if ( Ember.isEqual(system, item)) {
							isAssigned = false;
						}
					}
				}
			});
			return !isAssigned;
		});
	}).property('content.@each.isLoaded', 'controllers.userStoryTable.content.isLoaded')	
});

App.SystemChangesController = Ember.ArrayController.extend({
	itemController : 'system.change'	
});

App.SystemChangeController = Ember.ObjectController.extend({
	
});

App.SystemsController = Ember.ArrayController.extend({
	needs : ['system.changes']
});

App.SystemChangesModalController = Ember.ArrayController.extend({
	selectedFilter : null,
	itemControlller : 'systemChangeModal',
	needs : ['filter.system'],
	create : function(userstory) {
		
	}
});

App.SystemChangeModalController = App.ModalController.extend({
	
});

App.SelectFilterSystem = Ember.Select.extend({
	multiple : false,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id',
	prompt : ''
});

App.FilterSystemController = Ember.ArrayController.extend({
	sortProperties: ['name'],
    sortAscending: true
});

App.SystemChangeModalView = App.ModalView.extend({
	templateName : 'add-system-change-modal'
});