App = Ember.Application.create({
	LOG_TRANSITIONS: true
});

App.Manager = Ember.StateManager.create({
	enableLogging: true,
	states: {
		start: Ember.State.create({}),
		nothingSelected: Ember.State.create({}),
		projectSelected: Ember.State.create({}),
		epicSelected: Ember.State.create({
			projectId: 2,
			epicId: 1
		})
	}
});

App.Manager.transitionTo('epicSelected');


App.ApplicationView = Ember.View.extend({
	templateName: 'application'
});

App.Adapter = DS.RESTAdapter.extend({
    buildURL: function(record, suffix) {
      return 'rest' + this._super(record,suffix)
    }
});

App.Adapter.configure("plurals", 
	{"user_story": "user_stories"}
);

App.store = DS.Store.create({
    revision: 11,
	adapter: App.Adapter.create(),
  });

App.BootstrapControl = Ember.View.extend({
	classNames: ["control-group"],
	layoutName: "bootstrap-control"	
});

App.Router.map(function(){
	this.resource('project', {path: 'project/:project_id'});
	this.resource('epic', {path:'epic/:epic_id'});
});

App.IndexRoute = Ember.Route.extend({
	redirect: function(){
		alert("Redirecting from Index!");
		var epic = App.Epic.find(1);
		this.transitionTo('epic', epic);
	}	
});

App.ProjectController = Ember.ObjectController.extend({
	save: function(){
		var model = App.store.commit();
	}
});

App.AccItemView = Ember.View.extend({
	templateName: 'acc_item',
	classNames: ['accordion-group'],
	didInsertElement: function() {
		return Ember.run.next(this, function() {
			return this.$('.collapse').collapse({
				parent: "#accordion2"
			});
		});
	}
});