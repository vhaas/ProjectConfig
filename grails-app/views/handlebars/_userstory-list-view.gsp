<script type="text/x-handlebars" data-template-name="userstory-list">
	<form class="form-vertical" style="height: 50em; overflow:auto">
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
						{{view Ember.TextField disabledBinding="controller.disabledUserStory" valueBinding="name"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="goal" label="Ziel"}}
						{{view Ember.TextField disabledBinding="controller.disabledUserStory" valueBinding="goal"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="benefit" label="Nutzen"}}
						{{view Ember.TextField disabledBinding="controller.disabledUserStory" valueBinding="benefit"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="role" label="Rolle"}}
						{{view App.RoleSelect
							contentBinding="controller.controllers.roles.content"
							selectionBinding="role"
							valueBinding="role_id"
							prompt="Please select a role"}}
						<button class="btn btn-secondary" {{action "editRole" role}}{{bindAttr disabled="controller.disabledUserStory"}}>Rolle editieren</button>
					{{/view}}
					{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
						{{view Ember.TextArea disabledBinding="controller.disabledUserStory" valueBinding="description"}}
					{{/view}}
					{{#view App.BootstrapControl inputId="willGetDeleted" label="User Story löschen"}}
						{{view Ember.Checkbox disabledBinding="controller.disabledUserStory" checkedBinding="willGetDeleted"}}
					{{/view}}
					{{#if willGetDeleted}}
						<button class="btn btn-primary pull-right" {{action "deleteUserStory" id}}>User Story löschen</button>
					{{else}}
						{{#unless isDirty}}
							<button class="btn btn-secondary pull-right" {{action "enableUserStory" target="view"}}>Enable</button>
						{{else}}
							<button type="submit" class="btn btn-primary pull-right">Speichern</button>
							<button class="btn btn-secondary pull-right" {{action "saveAndCreate" target="view"}}>Speichern und neue User Story anlegen</button>
						{{/unless}}
					{{/if}}
					<div class="clearfix"/>
				</form>
			</section>
		{{/each}}
		<button class="btn btn-secondary pull-right" {{action "createUserStory"}}>Neue User Story anlegen</button>
		<button class="btn btn-secondary pull-right" {{action "createNewEpic" target="view"}}>Neue Epic anlegen</button>
		<button class="btn btn-secondary pull-right" {{action "switchToProject"}}>Projekte anzeigen</button>
	</form>
</script>