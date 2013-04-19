


//View
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