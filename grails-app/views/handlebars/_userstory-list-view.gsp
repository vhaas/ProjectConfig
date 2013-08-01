<script type="text/x-handlebars" data-template-name="userstory-list">
	<section class="form-vertical" style="height: 50em; overflow:auto">
		{{#each userStories in controller}}
			<section>
				<h4>
					{{name}}
					{{#if isDirty}}
						<i class="icon-exclamation-sign"></i>
					{{/if}}
				</h4>
				<form class="form-horizontal" {{action save on="submit"}}>
					{{#view App.BootstrapControl inputId="name" label="Name"}}
						{{view Ember.TextField disabledBinding="controller.isDisabled" valueBinding="name"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="goal" label="Goal"}}
						{{view Ember.TextField disabledBinding="controller.isDisabled" valueBinding="goal"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="benefit" label="Benefit"}}
						{{view Ember.TextField disabledBinding="controller.isDisabled" valueBinding="benefit"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="role" label="Role"}}
						{{view App.RoleSelect
							disabledBinding="controller.isDisabled"
							contentBinding="controller.controllers.roles.content"
							selectionBinding="role"
							valueBinding="role_id"
							prompt="Please select a role"}}
						{{#unless hasRole}}
							<button class="btn btn-secondary" {{action "editRole" role}}{{bindAttr disabled="controller.isDisabled"}}>Edit Role</button>
						{{/unless}}
						<button class="btn btn-secondary" {{action "createRole" this}}{{bindAttr disabled="controller.isDisabled"}}>Create new Role</button>
					{{/view}}
					{{#view App.BootstrapControl inputId="description" label="Description"}}
						{{view Ember.TextArea disabledBinding="controller.isDisabled" valueBinding="description"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="controller.willGetDeleted" label="Delete User Story"}}
						{{view Ember.Checkbox disabledBinding="controller.isDisabled" checkedBinding="controller.willGetDeleted"}}
					{{/view}}
					{{#if controller.willGetDeleted}}
						<button class="btn btn-primary pull-right" {{action "deleteUserstory" this}}>Delete User Story</button>
					{{else}}
						{{#unless isDirty}}
							{{#if controller.isDisabled}}
								<button class="btn btn-secondary pull-right" {{action "setEnabled" target="controller"}}>Enable</button>
							{{else}}
								<button class="btn btn-secondary pull-right" {{action "setDisabled" target="controller"}}>Disable</button>
							{{/if}}
						{{else}}
							<button type="submit" class="btn btn-primary pull-right">Save</button>
							<button class="btn btn-secondary pull-right" {{action "saveAndCreate" target="view"}}>Save and create new User Story</button>
						{{/unless}}
					{{/if}}
					<div class="clearfix"/>
				</form>
			</section>
		{{/each}}
	</section>
</script>