App = Ember.Application.create();

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

App.IndexController = Ember.Controller.extend({
	stories: App.UserStory.find({epic:App.Manager.get('currentState.epicId')}),
	project: App.Project.find(App.Manager.get('currentState.projectId')),
	epic: App.Epic.find(App.Manager.get('currentState.epicId'))
})
