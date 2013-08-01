App.RolesController = Ember.ArrayController.extend({
	itemController : 'role',
	sortProperties: ['name'],
    sortAscending: true
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
		var newRole = App.Role.createRecord(),
			_this = this;
		newRole.set('project', userStory.get('project'));
		newRole.one('didCreate', function() {			
			Ember.run.next(_this, function() {				
				console.log('New Role: ' + newRole);
				userStory.set('role', newRole);
				console.log('Role create log: ' + userStory.get('role'));				
				this.send('close');
			});
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