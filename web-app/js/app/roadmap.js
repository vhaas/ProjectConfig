App.RoadmapRoute = Ember.Route.extend({
	setupController: function(controller, model) {
		this.controller.set('content', model);
//		var projectId = this.modelFor('project').get('id');
		var projectId = model.get('project').get('id');
		var roadmapId = model.get('id');
		var mileStones = App.MileStone.find({roadmap:roadmapId});
		var userStories = App.UserStory.find({project:projectId});
		var filteredUserStories = App.UserStory.filter((
				function(userStory) {
				if (userStory) {
					if (userStory.get('project')) {
						if (userStory.get('project').get('id') === projectId) {
							var mileStones = userStory.get('mileStones');
							var boolean = true;
							mileStones.forEach(function(mileStone) {
								if (mileStone.get('roadMap')) {
									if (mileStone.get('roadMap').get('id') === roadmapId) {
										boolean = false;
									}
								}
							});
							return boolean;
						}
					}
				}
				return false;
			}));		
		this.controllerFor('userstorylist').set('content', filteredUserStories);
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
			alert("UserStory " + id + " was selected, Controller: " + this.get('controller'));
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
	    edit: function(roadmap) {
	        this.controllerFor('roadmap.modal').edit(roadmap);
	        this.send('openModal', 'roadmap.modal');
	    }
	}
});

App.RoadmapModalController = App.ModalController.extend({
	create : function () {
		alert('Called create');
		var roadmap = App.RoadMap.createRecord();
		roadmap.on('didCreate', this, function() {
			this.send('close');
	    });
	    this.set('model', roadmap);
	}
});

App.RoadmapModalView = App.ModalView.extend({
	templateName : 'modal2'
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
	}	
});

App.RoadmapView = Ember.View.extend({
	templateName : 'roadmap',
	parentView : 'project.index'
});

App.UserstorylistController = Ember.ArrayController.extend({
    sortProperties: ['name'],
    sortAscending: true,
	selection : null,
	active : true
});

App.MilestonesController = Ember.ArrayController.extend({
	needs: ['userstorylist'],
	sortProperties: ['orderId'],
    sortAscending: true,	
	doda: function() {
		alert(this.get('controllers').get('userstorylist').get('content'));
	}
});

App.MilestoneController = Ember.ObjectController.extend({
	createMileStone : function(milestone) {		
		milestone.get('store').commit();
		return this.transitionTo('roadmap', this.get('content').get('roadmap'));
	}
});

App.NewMilestoneModalView = Ember.View.extend({
	templateName : 'newMileStone',
	layoutName : 'modal',
	closeModale : function(event) {
		this.get('controller').get('content').deleteRecord();
		this.remove();
	},
	saveMilestone : function() {
		this.get('controller').createMileStone(this.get('controller').get('content'));
		this.remove();
	},
	resetForm : function() {
		this.set('name', '');
		this.set('description', '');
	},
	isNotDirty : function() {
		return !this.get('controller.content.isDirty')
	}.property('controller.content.isDirty').cacheable()
});

App.Select = Ember.Select.extend({
	multiple : true,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id'
});