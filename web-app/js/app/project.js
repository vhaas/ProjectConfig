App.ProjectRoute = Ember.Route.extend({
	renderTemplate: function() {		
	    this.render('roadmap-list', { 
	    	into: 'project',	    	 
	    	outlet: 'roadmap',	    	
	    	controller: 'roadmaps'
	    });
	}
});

App.ProjectController = Ember.ObjectController.extend({
	
});

App.ProjectView = Ember.View.extend({
	
});