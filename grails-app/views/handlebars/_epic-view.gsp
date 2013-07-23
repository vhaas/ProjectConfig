<g:render template="/handlebars/popup-view"></g:render>
<g:render template="/handlebars/role-editor-popup"></g:render>
<g:render template="/handlebars/accordion-views"></g:render>
<script type="text/x-handlebars" data-template-name="epic">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#/projects">Projects</a> <span class="divider">/</span></li>
					<li><a href="#/projects">{{project.name}}</a> <span class="divider">/</span></li>
					<li><a href="#">User Story Editor</a> <span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<section>
						<div class="accordion" id="accordion2">
	      					{{#each item in epics}}
       							{{view App.AccItemView contextBinding='item'}}
      						{{/each}}
    					</div>
					</section>
				</div>
				<div class="span8">
	<!-- Epic -->
					<section>
						<h4>
							{{name}}
							{{#if isDirty}}
								<i class="icon-exclamation-sign"></i>
							{{/if}}
						</h4>
						<form class="form-horizontal" {{action save on="submit"}}>
							{{#view App.BootstrapControl inputId="name" label="Name"}}
								{{view Ember.TextField disabledBinding="controller.disabledEpic" valueBinding="name"}}
							{{/view}}
							{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
								{{view Ember.TextArea disabledBinding="controller.disabledEpic" valueBinding="description"}}
							{{/view}}
							{{#unless isDirty}}
								<button class="btn btn-secondary pull-right" {{action "enableEpic" target="view"}}>Enable</button>
							{{else}}
								<button type="submit" class="btn btn-primary pull-right">Speichern</button>
							{{/unless}}
							<div class="clearfix"/>
						</form>
					</section>
	<!-- User Stories -->
					{{#each userStories}}
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
									{{view App.Select
										disabledBinding="controller.disabledUserStory"
										contentBinding="App.SelectRolesController"
										selectionBinding="role"
										valueBinding="role_id"
										prompt="Please select a role"}}
									<button class="btn btn-secondary" {{action "openModal" role}}{{bindAttr disabled="controller.disabledUserStory"}}>Rolle editieren</button>
								{{/view}}
								{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
									{{view Ember.TextArea disabledBinding="controller.disabledUserStory" valueBinding="description"}}
								{{/view}}
								{{#view App.BootstrapControl inputId="willGetDeleted" label="User Story löschen"}}
									{{view Ember.Checkbox disabledBinding="controller.disabledUserStory" checkedBinding="willGetDeleted"}}
								{{/view}}
								{{#if willGetDeleted}}
									<button class="btn btn-primary pull-right" {{action "deleteUserStory" id}}>User Story löschen</button>
								{{/if}}
								{{#unless isDirty}}
									<button class="btn btn-secondary pull-right" {{action "enableUserStory" target="view"}}>Enable</button>
								{{else}}
									<button type="submit" class="btn btn-primary pull-right">Speichern</button>
									<button class="btn btn-secondary pull-right" {{action "saveAndCreate" target="view"}}>Speichern und neue User Story anlegen</button>
								{{/unless}}
								<div class="clearfix"/>
							</form>
						</section>
					{{/each}}
					<button class="btn btn-secondary pull-right" {{action "createUserStory"}}>Neue User Story anlegen</button>
					<button class="btn btn-secondary pull-right" {{action "createNewEpic" target="view"}}>Neue Epic anlegen</button>
					<button class="btn btn-secondary pull-right" {{action "switchToProject"}}>Projekte anzeigen</button>
				</div>
			</div>
		</div>
</script>