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