App.ProjectsRoute = Ember.Route.extend({
	model: function() {
		return App.Project.find();
	},
	events : {
		select : function(id) {
			var project = App.Project.find(id);
//			alert('Project passed to project view: ' + project);
			this.transitionTo('project', project);
		}
	}
});

App.ProjectsController = Ember.ArrayController.extend({
    sortProperties: ['name'],
    sortAscending: false
});

App.ProjectListView = Ember.View.extend({
	templateName : 'project-acc-item',
	classNames : [ 'accordion-group' ],
	didInsertElement : function() {
		return Ember.run.next(this, function() {
			return this.$('.collapse').collapse({
				parent : "#accordion1"
			});
		});
	}
});
