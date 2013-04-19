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
	role: DS.belongsTo("App.Role"),
	willGetDeleted: DS.attr("boolean", {defaultValue: false})
});

App.Role = DS.Model.extend({
	name: DS.attr("string"),
	description: DS.attr("string"),
	project: DS.belongsTo("App.Project")
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

App.EpicRoute = Ember.Route.extend({
	events: {
		openModal: function(role) {
			var roleController = this.controllerFor('role');
			roleController.set('content', role);
			var modalView = App.ModalView.create({				
				controller: roleController
			});
			modalView.append();
		}
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
	createUserStory: function() {
		var userStory = App.UserStory.createRecord();
		userStory.set('epic', this.content);
		return userStory;
	},
	createEpic: function() {
		var epic = App.Epic.createRecord();
		epic.set('project', 1);
		return epic;
	},
	deleteUserStory: function(id) {
		var model = App.UserStory.find(id);
		model.deleteRecord();
	    App.store.commit();
	}
});

App.RoleController = Ember.ObjectController.extend({
	save: function() {		
		var model = App.store.commit();
	},
	create: function() {
		var role = App.Role.createRecord();
		role.set('project', App.Project.find(1));
		return role;
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

App.SelectController = Ember.ArrayController.extend({
	selection: null,
	active: true
});

App.Select = Ember.Select.extend({
    multiple: false,
    optionLabelPath: "content.name",
    optionValuePath: "content.id"
});

App.SelectRolesController = App.SelectController.create();
App.SelectRolesController.set("content", App.Role.find());

App.PopupView = Ember.View.extend({
	layoutName: 'popup'
});

App.EpicView = Ember.View.extend({
	createNewEpic: function() {
		this.get('controller').set('content', this.get('controller').createEpic());
	},
	isNotDirty: function() { 
		return !this.get('controller.content.isDirty') 
	}.property('controller.content.isDirty').cacheable(),
	saveAndCreate: function() {
		this.get('controller').save();
		this.get('controller').createUserStory();
	}
});

App.ModalView = Ember.View.extend({
	templateName: 'roleEditor',
	layoutName: 'modal',
	closeModal: function(event) {
		this.remove();
	},
	saveRole: function() {
		alert("Saved Role: " + this.get('controller').get('content'));
		this.get('controller').save();
		this.remove();
	},     
	createNewRole: function() {
		this.get('controller').set('content', this.get('controller').create());
	},      
	resetForm: function() {
		this.set('name', '');
		this.set('description', '');
	},
	isNotDirty: function(){ 
		return !this.get('controller.content.isDirty') 
	}.property('controller.content.isDirty').cacheable()
});