App.SystemchangeRoute = Ember.Route.extend({
	setupController : function(controller, model) {
		this.controllerFor('user.story.table').set('content', model);
		this.controllerFor('user.stories.table').set('selected', model);
		var projectId = model.get('project').get('id');
		this.controllerFor('user.stories.table').set('content', App.UserStory.find({project:projectId}));
		this.controllerFor('systems.table').set('content', App.System.find({project:projectId}));
    	this.controllerFor('system.changes.modal').set('content', App.SystemChange.find({project:projectId}));
    	this.controllerFor('system.changes.list').set('content', model.get('systemChanges'));
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
		}),
		this.render('systemchange-list', {
			into : 'system-change-input',
			outlet : 'system-changes',
			controller : 'system.changes.list'
		})
	},
	events : {
		editRole: function(role) {
	        this.controllerFor('role.modal').edit(role);
	        this.send('openModal', 'role.modal');
	    },
	    addSystemChange : function(userStory) {
	    	this.controllerFor('system.changes.modal').create(userStory);
	    	this.send('openModal', 'system.changes.modal');
	    },
	    doda : function(item) {
	    	console.log('SystemChanges: ' + item.get('systemChanges'));
	    },
	    leaveFormDirty : function(model) {
			this.controllerFor('leave.dirty.modal').leaveDirty(model, 'epic');
			this.send('openModal', 'leave.dirty.modal');
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
	needs : ['system.changes.list'],
	sortProperties: ['name'],
    sortAscending: true,
	selected : null
});

App.UserStoryTableController = Ember.ObjectController.extend({
	
});

App.SystemsTableController = Ember.ArrayController.extend({
	needs : ['userStoryTable'],
	filteredContent : (function() {
		var content = this.get('content'),
			userstory = this.get('controllers').get('userStoryTable').get('content');
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
						if (Ember.isEqual(system, item)) {
							isAssigned = false;
						}
					}
				}
			});
			return !isAssigned;
		});
	}).property('content.@each.isLoaded', 'controllers.userStoryTable.content.isLoaded')	
});

App.SystemChangesListController = Ember.ArrayController.extend({
	itemController : 'systemChangeItem',
	enabledSystemChange : null
});

App.SystemChangeItemController = Ember.ObjectController.extend({
	isDisabled : (function() {
		if (Ember.isNone(this.get('parentController').get('enabledSystemChange'))) {
			return true;
		} else {
			return !Ember.isEqual(this.get('content'), this.get('parentController').get('enabledSystemChange'));
		}
	}).property('parentController.enabledSystemChange', 'content'),
	editMode : false,
	toggleEditMode : (function() {
		if (this.get('editMode') === true) {
			this.get('parentController').set('enabledSystemChange', this.get('content'));
		} else {
			this.get('parentController').set('enabledSystemChange', null);
		}
	}).observes('editMode'),
	leaveDirty : (function() {
		if (!Ember.isNone(this.get('content'))) {
			if (this.get('content').get('isDirty')) {
				if (!Ember.isEmpty(this.get('content').get('id'))) {
					this.send('leaveFormDirty', this.get('content'));
				}
			}
		}
	}).observes('isDisabled'),
	save : function() {
		this.get('model.transaction').commit();
		if (Ember.isNone(this.get('content').get('id'))) {
			this.get('model').one('didCreate', this, function() {
				this.setDisabled();
			});
		} else {
			this.get('model').one('didUpdate', this, function() {
				this.setDisabled();
			});
		}
	},
	hasSystem : (function() {
		return Ember.isNone(this.get('model').get('system'));
	}).property('content.system')
});

App.SystemsController = Ember.ArrayController.extend({
	needs : ['systemChanges'],
	sortProperties: ['name'],
    sortAscending: true
});

App.SystemChangesModalController = Ember.ArrayController.extend({
	itemController : 'systemChangeModal',
	needs : ['systemsTable'],
	save : function() {
		var sysch = this.get('selectedUserStory').get('systemChanges').pushObject(this.get('selectedSystemChange'));
		sysch.one('didUpdate', this, function() {
			this.send('close');
		});
		sysch.get('store').commit();
//		this.get('model.transaction').commit();
	},	
	close : function() {
		var model = this.get('model'), transaction;
		this.set('selectedSystemChange', null);
		if (model) {
			 transaction = model.get('transaction');
			 if (transaction)
				transaction.rollback();
			 if (model.get('errors'))
					model.set('errors', null);
		}

		this.send("closeModal");
	},	
	shouldDisableSubmit : function() {
		return Ember.isNone(this.get('selectedSystemChange'));
	}.property('selectedSystemChange'),
	selectedFilter : null,
	create : function(userstory) {
		this.set('selectedUserStory',userstory);
	},
	selectedSystemChange : null,
	selectedUserStory : null,
//	filteredContent : (function() {
//		if (!Ember.isNone(this.get('selectedFilter'))) {
//			var content = this.get('arrangedContent'), 
//				systemFilter = this.get('selectedFilter');
//			if (!content) {
//				return;
//			}
//			content = content.filter(function(item) {
//				if (Ember.isEqual(item, systemFilter)) {
//					return true;
//				} else {
//					return false;
//				}
//			});
//			return Ember.ArrayProxy.create({
//				content : Ember.A(content),
//				lookupItemController : this.lookupItemController
//			});
//		} else {
//			return this.get('arrangedContent');
//		}
//	}).property('content.@each.system', 'selectedFilter')
	doCall : (function() {
		console.log(this.get('selectedSystemChange'));
	}).observes('selectedSystemChange')
});

App.SystemChangeModalController = App.ModalController.extend({
	isSelected : (function() {
		var selected = this.get('parentController').get('selectedSystemChange');
		return !Ember.isEqual(this.get('content'), selected);
	}).property('parentController.selectedSystemChange', 'content'),
	selectSystemChange : function(systemChange) {
		this.get('parentController').set('selectedSystemChange', systemChange);
	}
});

App.SelectFilterSystem = Ember.Select.extend({
	multiple : false,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id'
});

App.SystemChangesModalView = App.ModalView.extend({
	templateName : 'add-system-change-modal'
});

App.SystemSelect = Ember.Select.extend({
	multiple : false,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id'
});

App.AdaptionTypeSelect = Ember.Select.extend({
	multiple : false,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id'
});