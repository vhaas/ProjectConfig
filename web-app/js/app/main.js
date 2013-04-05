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
	project: DS.belongsTo("App.Project"),
	userStories: DS.hasMany("App.UserStory"),
	href: (function() {		
	    return "#" + this.get('id');
	  }).property('id').cacheable()
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

App.EpicController = Ember.ObjectController.extend({
	save: function(){
		alert("Want to store the epic!: "+this.get("content").get("name")+" "+this.get("content").get("isDirty"));
		var model = App.store.commit();		
	},
	epics: App.Epic.find(),
	test: function(){
		alert("this is dog");		
	},
	create: function() {
		var userStory = App.UserStory.createRecord();
		userStory.set('epic',this.content);
		return userStory;
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

App.RolesController = Ember.ArrayController.extend({
	selection: null,
	active: true
});

App.RoleSelect = Ember.Select.extend({
    multiple: false,
    optionLabelPath: "content.name",
    optionValuePath: "content.id"
});

App.SelectController = App.RolesController.create();
App.SelectController.set("content", App.Role.find());

App.PopupView = Ember.View.extend({
	  layoutName: 'popup'
});