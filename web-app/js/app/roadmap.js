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
			alert("UserStory " + id + " was selected");
		},
		goBack : function(id) {
			var project = App.Project.find(id);
			this.transitionTo('project', project);
		}
	}
});

App.RoadmapController = Ember.ObjectController.extend({	
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
		var kitten = this.get('model');
		kitten.save().then(function() {
			this.transitionToRoute('index');
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

});

App.SelectController = Ember.ArrayController.extend({
	selection : null,
	active : true
});

App.Select = Ember.Select.extend({
	multiple : true,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id'
});

//App.SelectuserstoryController = App.SelectController.create();
//App.SelectuserstoryController.set('content', App.UserStory.find());
	