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
		this.render('userstory', {
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
		},
		leaveFormDirty : function(model) {
			this.controllerFor('leave.dirty.modal').leaveDirty(model, 'epic');
			this.send('openModal', 'leave.dirty.modal');
		},
		createUserstory : function(epic) {
			this.get('controller').get('controllers').get('userstories').create(epic);			
		},
		deleteUserstory : function(userstory) {
			this.controllerFor('confirm.delete').confirmDelete(userstory);
	    	this.send('openModal', 'confirm.delete');
		}
	}
});

App.LeaveDirtyModalController = Ember.ObjectController.extend({
	leaveDirty : function(model, afterLeaveRoute) {
		this.set('model', model);
		this.set('afterLeaveRoute', afterLeaveRoute);
	},
	confirmSaveChanges : function() {
		var model = this.get('model'),
			after = this.get('afterLeaveRoute');
		
		model.on('didUpdate', this, function() {
			this.close();
			if (!Ember.isNone(after)) {
				this.transitionToRoute(after);
			}
		});
		this.get('model.transaction').commit();
	},
	confirmRollbackChanges : function() {
		this.get('model.transaction').rollback();
		this.close();
	},
	close : function() {
		this.send('closeModal');
	}
});

App.LeaveDirtyModalView = App.ModalView.extend({
	templateName : 'confirm-changes-modal'
});

App.EpicUserstoriesController = Ember.ArrayController.extend({
	
});

// Controller
App.EpicsController = Ember.ArrayController.extend({
	needs : ['epic.userstories'],
	sortProperties: ['name'],
    sortAscending: true,
	itemController : 'epic',
	select : function(epic) {
		this.transitionToRoute('epic', epic);
	}
});

App.EpicController = Ember.ObjectController.extend({
	needs : ['userstories'],
	isDisabled : true,
	setEnabled : function() {
		this.get('controllers').get('userstories').set('enabledUserstory', null);
		this.set('isDisabled', false);
	},
	setDisabled : function() {
		this.set('isDisabled', true);
	},
	userstoryEnabled : (function() {
		if (!Ember.isNone(this.get('controllers').get('userstories').get('enabledUserstory'))) {
			this.setDisabled();
		}
	}).observes('controllers.userstories.enabledUserstory'),
	leaveDirty : (function() {
		if (this.get('content').get('isDirty')) {
			this.send('leaveFormDirty', this.get('content'));
		}
	}).observes('isDisabled'),
	save : function() {
		this.get('model.transaction').commit();
		this.get('model').one('didUpdate', this, function() {
			this.setDisabled();
		});
	},
	create : function() {
		var epic,
	      _this = this;
		epic = App.Epic.createRecord({});
		epic.set('project', this.get('content').get('project'));
		epic.one('didCreate', function() {
	      return Ember.run.next(_this, function() {
	        return this.transitionTo("epic", epic);
	      });
	    });
	    return epic.get("store").commit();
	}
	
});

App.UserstoriesController = Ember.ArrayController.extend({	
	itemController : 'userstory',
	enabledUserstory : null,
	create : function(epic) {
		var newUserstory = App.UserStory.createRecord();
		newUserstory.set('project', epic.get('project'));
		newUserstory.set('epic', epic);
		this.set('enabledUserstory', newUserstory);
	}
});

App.UserstoryController = Ember.ObjectController.extend({
	needs : ['roles'],
	willGetDeleted : false,
	isDisabled : (function() {
		if (Ember.isNone(this.get('parentController').get('enabledUserstory'))) {
			console.log('is true');
			return true;
		} else {
			return !Ember.isEqual(this.get('content'), this.get('parentController').get('enabledUserstory'));
		}
	}).property('parentController.enabledUserstory', 'content'),
	setEnabled : function() {
		console.log('controllersBeforeEnable: ' + this.get('parentController').get('enabledUserstory'));
		this.get('parentController').set('enabledUserstory', this.get('content'));
		console.log('controllersAfterEnable: ' + this.get('parentController').get('enabledUserstory'));
	},
	setDisabled : function() {
		this.get('parentController').set('enabledUserstory', null);
	},
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
	remove : function() {
		this.get('content').deleteRecord();
		this.get('model.transaction').commit();
	},
	hasRole : (function() {
		return Ember.isNone(this.get('model').get('role'));
	}).property('content.role')
});

App.UserstoryView = Ember.View.extend({
	templateName : 'userstory-list'
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