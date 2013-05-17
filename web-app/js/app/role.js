App.RoleController = Ember.ObjectController.extend({
	save: function() {		
		var model = App.store.commit();
	},
	create: function() {
		var role = App.Role.createRecord();
		var projectId = this.content.get('project').get('id');		
		role.set('project', App.Project.find(projectId));
		return role;
	}
});

App.SelectRolesController = App.SelectController.create();
App.SelectRolesController.set("content", App.Role.find());