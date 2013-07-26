App.RoadmapRoute = Ember.Route.extend({
	setupController: function(controller, model) {
		this.controller.set('content', model);
//		var projectId = this.modelFor('project').get('id');
		var projectId = model.get('project').get('id');
		var roadmapId = model.get('id');
		var mileStones = App.MileStone.find({roadmap:roadmapId});
		var userStories = App.UserStory.find({project:projectId});		
//		var filteredUserStories = App.UserStory.filter((
//				function(userStory) {
//				if (userStory) {
//					if (userStory.get('project')) {
//						if (userStory.get('project').get('id') === projectId) {
//							var mileStones = userStory.get('mileStones');
//							var boolean = true;
//							mileStones.forEach(function(mileStone) {
//								if (mileStone.get('roadMap')) {
//									if (mileStone.get('roadMap').get('id') === roadmapId) {
//										boolean = false;
//									}
//								}
//							});
//							return boolean;
//						}
//					}
//				}
//				return false;
//			}));		
		this.controllerFor('userstorylist').set('content', userStories);
		this.controllerFor('milestones').set('content', mileStones);
	},
	model : function(params) {
	    return App.RoadMap.find(params.roadmap_id);
	},
	renderTemplate : function() {
		this.render('roadmap', {
			into : 'application',
			controller : 'roadmap'
		}),
		this.render('unselected-userstories', {
			into : 'roadmap',
			outlet : 'unselected-user-stories',
			controller : 'userstorylist'
		}),
		this.render('milestone', {
			into : 'roadmap',
			outlet : 'mileStones',
			controller : 'milestones'
		})
	},
	events : {
		select : function(id) {
			var userstory = App.UserStory.find(id);
			alert("UserStory " + id + " was selected, Controller: " + userstory.get('controller'));
		},
		openNewMileStoneModal : function(roadmap) {
			var milestoneController = this.controllerFor('milestone');
			milestoneController.set('content', App.MileStone.createRecord({}));
			milestoneController.set('content.roadMap', roadmap);
			milestoneController.set('content.project', roadmap.get('project'));
			var modalView = App.NewMilestoneModalView.create({
				controller : milestoneController
			});
			modalView.append();
		},
	    createRoadmap: function(roadmap) {
	        this.controllerFor('roadmap.modal').create(roadmap);
	        this.send('openModal', 'roadmap.modal');
	    },
	    editMilestone: function(milestone) {
	        this.controllerFor('milestone.modal').edit(milestone);
	        this.send('openModal', 'milestone.modal');
	    },
	    createMilestone: function(roadmap) {
	        this.controllerFor('milestone.modal').create(roadmap);
	        this.send('openModal', 'milestone.modal');
	    },
	}
});

App.RoadmapModalController = App.ModalController.extend({
	create : function (roadmap) {
		var newRoadmap = App.RoadMap.createRecord();
		newRoadmap.set('project', roadmap.get('project'));
		newRoadmap.on('didCreate', this, function() {
			this.transitionToRoute('roadmap', newRoadmap);
			this.send('close');
	    });
	    this.set('model', newRoadmap);
	}
});

App.RoadmapModalView = App.ModalView.extend({
	templateName : 'roadmap-modal'
});

App.MilestoneModalController = App.ModalController.extend({
	create : function (roadmap) {
		var milestone = App.MileStone.createRecord();
		milestone.set('roadMap', roadmap);
		milestone.set('project', roadmap.get('project'));
		milestone.on('didCreate', this, function() {
			this.transitionToRoute('roadmap', roadmap);
			this.send('close');
	    });
	    this.set('model', milestone);
	}
});

App.MilestoneModalView = App.ModalView.extend({
	templateName : 'milestone-modal'
});

App.RoadmapController = Ember.ObjectController.extend({
	needs: ['milestones'],
	createRoadMap : function() {
		var roadmap, _this = this;
		roadmap = App.RoadMap.createRecord({});
		roadmap.set('project', this.content.get('project'));
		roadmap.set('name', 'New RoadMap');
		roadmap.set('description', 'New RoadMap');
		roadmap.one('didCreate', function() {
			return Ember.run.next(_this, function() {
				return this.transitionTo('roadmap', roadmap);
			});
		});
		return roadmap.get('store').commit();
	},
	save : function() {
		roadmap.save().then(function() {
			this.transitionToRoute('roadmap', roadmap);
		}.bind(this));
	},	
	isNotDirty : function() {
		return !this.get('isDirty');
	}.property('isDirty')
});

App.RoadmapView = Ember.View.extend({
	templateName : 'roadmap',
	parentView : 'project.index'
});

App.UserstorylistController = Ember.ArrayController.extend({
    sortProperties: ['name'],
    sortAscending: true,
	selection : null,
	active : true,
	removeItem: function(value){
//        var obj = this.findProperty('id', value);
//        this.removeObject(obj);
//		alert('The following item will be removed: ' + value);
    }
});

App.MilestonesController = Ember.ArrayController.extend({
//	needs: ['userstorylist'],
	itemController : 'milestone',
	sortProperties: ['orderId'],
    sortAscending: true,
    doda : function() {
    	alert('OrderId: ' + this.content.get('order_id'));
    }
});

App.MilestoneController = Ember.ObjectController.extend({
//	createMileStone : function(milestone) {		
//		milestone.get('store').commit();
//		return this.transitionTo('roadmap', this.get('content').get('roadmap'));
//	},
	needs: ['userstorylist'],
	selectedUserStory : null,
	selectedChanged : function() {
//		alert('UserStory was selected: ' + this.get('selectedUserStory'));
//		alert('Controller: ' + this.get('controllers').get('userstorylist'));		
		this.get('controllers').get('userstorylist').removeItem(this.get('selectedUserStory').get('id'));
		this.get('content').get('userStories').pushObject(this.get('selectedUserStory'));
		this.get("model.transaction").commit();
//		this.get('selectedUserStory').get('mileStones').pushObject(App.MileStone.find(this.get('content').get('id')));
//		this.get('selectedUserStory').get('store').commit();
	}.observes('selectedUserStory'),
	controllercheck : function() {
		alert(this);
	}
});

App.MileStoneView = Ember.View.extend({
	mouseover : function() {
		
	}
})

App.Select = Ember.Select.extend({
	multiple : false,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id',
	prompt : 'Select user story'
});