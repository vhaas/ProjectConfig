window.App = Ember.Application.create({
	LOG_TRANSITIONS : true
});

App.ApplicationRoute = Em.Route.extend({
	events : {
		openModal : function(modal) {
			this.render(modal, {
				into : 'application',
				outlet : 'modal'
			});
		},
		closeModal : function() {
			App.animateModalClose().then(function() {
				this.render('empty', {
					into : 'application',
					outlet : 'modal'
				});
			}.bind(this));
		}
	}
});

App.ApplicationView = Ember.View.extend({
	templateName : 'application'
});

App.Adapter = DS.RESTAdapter.extend({
	buildURL : function(record, suffix) {
		return 'rest' + this._super(record, suffix)
	},
//	dirtyRecordsForHasManyChange : function(dirtySet, record, relationship) {
//		alert(dirtySet);
//		relationship.childReference.parent = relationship.parentReference;
//		this._dirtyTree(dirtySet, record);
//	},
	serializer : DS.RESTSerializer.extend({
		addHasMany : function(hash, record, key, relationship) {
			var ids = record.get(relationship.key).map(function(item) {
				return parseInt(item.get('id'));
			});
		    hash[key] = ids;
		}
	})
});

App.Adapter.configure("plurals", {
	"user_story" : "user_stories"
});

App.Store = DS.Store.extend({
	adapter : 'App.Adapter'
});

App.BootstrapControl = Ember.View.extend({
	classNames : [ "control-group" ],
	layoutName : "bootstrap-control"
});

App.Router.map(function() {
	this.resource('projects', { path : '/' });
	this.resource('project', { path : 'project/:project_id' }, function() {
		this.resource('roadmap', { path : 'roadmap/:roadmap_id' });
		this.resource('epic', { path : 'epic/:epic_id' });
		this.resource('system', { path : 'system/:system_id' });
		this.resource('systemchange', {path : 'systemchangeinput/:user_story_id'});
	});
});

App.IndexRoute = Ember.Route.extend({
	redirect : function() {
		this.transitionTo('projects');
	}
});

//App.Router.map(function() {
//	this.resource('projects', {
//		path : 'projects'
//	});
//	this.resource('project', {
//		path : 'project/:project_id'
//	});
//	this.resource('epic', {
//		path : 'epic/:epic_id'
//	});
//	this.resource('roadmaps', {
//		path : 'roadmaps'
//	});
//	this.resource('roadmap', {
//		path : 'roadmap/:roadmap_id'
//	});
//});

//App.AccItemView = Ember.View.extend({
//	templateName : 'acc_item',
//	classNames : [ 'accordion-group' ],
//	didInsertElement : function() {
//		return Ember.run.next(this, function() {
//			return this.$('.collapse').collapse({
//				parent : "#accordion2"
//			});
//		});
//	}
//});

//App.SelectController = Ember.ArrayController.extend({
//	selection : null,
//	active : true
//});

//App.Select = Ember.Select.extend({
//	multiple : true
//	optionLabelPath : "content.name",
//	optionValuePath : "content.id"
//});