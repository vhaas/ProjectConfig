App = Ember.Application.create({
	LOG_TRANSITIONS: true
});

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
//	this.resource('projects', function() {
//		this.resource('project', function() {
//			this.resource('epics', function() {
//				this.resource('epic', {path:'project/:epic_id'});
//				})
//			})
//		})
	this.resource('projects', {path: 'projects'});
	this.resource('project', {path: 'project/:project_id'});	
	this.resource('epic', {path: 'epic/:epic_id'});
//	this.resource('epics', function() {
//		this.resource('epic', {path:'epic/:epic_id'});
//	})
});

App.IndexRoute = Ember.Route.extend({
	redirect: function(){		
		this.transitionTo('projects');
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