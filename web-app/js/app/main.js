App = Ember.Application.create({
	LOG_TRANSITIONS: true
});

App.Manager = Ember.StateManager.create({
	enableLoggin: true,
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

App.Epic = DS.Model.extend({
	name: DS.attr("string"),
	description: DS.attr("string"),
	project: DS.belongsTo("App.Project")
});

App.Project = DS.Model.extend({
	name: DS.attr("string"),
	description: DS.attr("string")
});

App.UserStory = DS.Model.extend({
	name: DS.attr("string"),
	description: DS.attr("string"),
	goal: DS.attr("string"),
	benefit: DS.attr("string"),
	roadMap: DS.belongsTo("App.RoadMap"),
	epic: DS.belongsTo("App.Epic"),
	role: DS.belongsTo("App.Role")	
});

App.Role = DS.Model.extend({
	name: DS.attr("string"),
	description: DS.attr("string")
});

App.RoadMap = DS.Model.extend({
	name: DS.attr("string"),
	description: DS.attr("string")
});


App.BootstrapControl = Ember.View.extend({
	classNames: ["control-group"],
	layoutName: "bootstrap-control",
	isNotDirty: function(){
		alert("got dirty");
	    return !this.get('controller.content.isDirty') 
	}.property('controller.content.isDirty').cacheable()	
});

App.Router.map(function(){
	this.resource('project', {path: 'project/:project_id'});
	this.resource('epic', {path:'epic/:epic_id'});
});


App.IndexRoute = Ember.Route.extend({
	redirect: function(){
		alert("Redirecting from Index!");
		var epic = App.Epic.find(2);
		this.transitionTo('epic', epic);
	}
});

App.ProjectController = Ember.ObjectController.extend({
	save: function(){
		var model = App.store.commit();
	}	
});

App.EpicController = Ember.ObjectController.extend({
	save: function(){
		alert("Want to store the epic!: "+this.get("content").get("name")+" "+this.get("content").get("isDirty"));
		var model = App.store.commit();		
	}	
});


App.initialize();