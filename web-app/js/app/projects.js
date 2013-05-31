App.ProjectsRoute = Ember.Route.extend({
	model: function() {
		return App.Project.find();
	},
	events : {
		select : function(id) {
			var project = App.Project.find(id);
			this.transitionTo('project', project);
		}
	}
});

App.ProjectsController = Ember.ArrayController.extend({
	itemController: 'project'
});

App.ProjectController = Ember.ObjectController.extend({
	
});

App.ProjectListView = Ember.View.extend({
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
