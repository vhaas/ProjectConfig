App.RolesController = Ember.ArrayController.extend({
	itemController : 'role'
});

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

App.RoleModalController = App.ModalController.extend({
	create : function (userStory) {
		var newRole = App.Role.createRecord();
		newRole.set('project', userStory.get('project'));
		newRole.on('didCreate', this, function() {
			this.send('close');
	    });
	    this.set('model', newRole);
	}
});

App.RoleModalView = App.ModalView.extend({
	templateName : 'basic-modal',
	className : 'Role'
});

App.RoleSelect = Ember.Select.extend({
	multiple : false,
	optionLabelPath : 'content.name',
	optionValuePath : 'content.id',	
});