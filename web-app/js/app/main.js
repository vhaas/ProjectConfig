window.App = Ember.Application.create({
	LOG_TRANSITIONS : true
});

App.ApplicationView = Ember.View.extend({
	templateName : 'application'
});

App.Adapter = DS.RESTAdapter.extend({
	buildURL : function(record, suffix) {
		return 'rest' + this._super(record, suffix)
	}
});

App.Adapter.configure("plurals", {
	"user_story" : "user_stories"
});

//App.store = DS.Store.create({
//	revision : 11,
//	adapter : App.Adapter.create(),
//});

App.Store = DS.Store.extend({
	adapter : 'App.Adapter'
});

//App.Store = DS.Store.extend({
//	revision : 11,
//	adapter : DS.GrailsAdapter.create()
//});

App.BootstrapControl = Ember.View.extend({
	classNames : [ "control-group" ],
	layoutName : "bootstrap-control"
});

App.Router.map(function() {
	this.resource('projects', { path : 'projects' });
	this.resource('project', { path : 'project/:project_id' }, function() {
		this.resource('roadmap', { path : 'roadmap/:roadmap_id' });
	});
});

App.IndexRoute = Ember.Route.extend({
	redirect : function() {
		this.transitionTo('projects');
	}
});

//App.Router.map(function() {
//	this.resource('projects', {
//		path : 'projects'
//	});
//	this.resource('project', {
//		path : 'project/:project_id'
//	});
//	this.resource('epic', {
//		path : 'epic/:epic_id'
//	});
//	this.resource('roadmaps', {
//		path : 'roadmaps'
//	});
//	this.resource('roadmap', {
//		path : 'roadmap/:roadmap_id'
//	});
//});

//App.AccItemView = Ember.View.extend({
//	templateName : 'acc_item',
//	classNames : [ 'accordion-group' ],
//	didInsertElement : function() {
//		return Ember.run.next(this, function() {
//			return this.$('.collapse').collapse({
//				parent : "#accordion2"
//			});
//		});
//	}
//});

//App.SelectController = Ember.ArrayController.extend({
//	selection : null,
//	active : true
//});

//App.Select = Ember.Select.extend({
//	multiple : true
//	optionLabelPath : "content.name",
//	optionValuePath : "content.id"
//});