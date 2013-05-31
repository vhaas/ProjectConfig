App.RoadmapRoute = Ember.Route.extend({		
	setupController: function (controller, model){
	    console.log('sc', model);
	    this._super(this, arguments);
	}
});

// Controller
App.RoadmapController = Ember.ObjectController.extend({	
});

App.RoadmapsController = Ember.ArrayController.extend({
	itemController: 'roadmap'
});

App.RoadmapListView = Ember.View.extend({
	templateName : 'acc_item',
	classNames : [ 'accordion-group' ],
	didInsertElement : function() {
		return Ember.run.next(this, function() {
			return this.$('.collapse').collapse({
				parent : "#accordion2"
			});
		});
	}
});