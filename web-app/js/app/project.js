App.ProjectIndexRoute = Ember.Route.extend({
	setupController: function(controller, model) {
		this.controllerFor('roadmaplist').set('model', App.RoadMap.find());
	},
	renderTemplate : function() {
		this.render('roadmap-list', {
			into : 'project',
			outlet : 'roadmap',
			controller : 'roadmaplist'
		});
	},
	events : {
		select : function(id) {
			alert('RoadMap ID: ' + this.get('controller').get('controllers.roadmaplist').get('content').get('firstObject'));
//			var roadmap = App.RoadMap.find(id);
//			this.transitionTo('roadmap', roadmap);
		}
	}
});

App.ProjectIndexController = Ember.ObjectController.extend({
	needs: 'roadmaplist'
});

App.RoadmaplistController = Ember.ArrayController.extend({
});

App.RoadmapListView = Ember.View.extend({
	templateName : 'acc_roadmap_item',
	classNames : [ 'accordion-group' ],
	didInsertElement : function() {
		return Ember.run.next(this, function() {
			return this.$('.collapse').collapse({
				parent : "#accordion2"
			});
		});
	}
});