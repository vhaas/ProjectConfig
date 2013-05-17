
<script type="text/x-handlebars" data-template-name="index">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">{{project.name}}</a> <span class="divider">/</span></li>
					<li><a href="#">User Story Editor</a> <span class="divider">/</span></li>
					<li class="active">{{epic.name}}</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="span4">
				  <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="200">
					{{#each user_story in stories}}
					<li><a href="#">{{user_story.name}}</a></li>
					{{/each}}
				  </ul>
				</div>
				<div class="span8">
				  <section>
					<h4>{{epic.name}}</h4>
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="description">Beschreibung</label>
							<div class="controls controls-row">
								<span>
								{{epic.description}}
								</span>
							</div>
						</div>
						<button type="button" class="btn btn-primary pull-right" disabled="disabled">Bearbeiten</button>
						<div class="clearfix"/>
					</form>
				  </section>
				  {{#each stories}}
					  <section>
						<h4>{{name}}</h4>
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="name">Name</label>
								<div class="controls controls-row">
									<input type="text" class="input-block-level" name="name" {{bindAttr value="name"}}/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="goal">Ziel</label>
								<div class="controls controls-row">
									<input type="text" class="input-block-level" name="goal" {{bindAttr value="goal"}}/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="benefit">Nutzen</label>
								<div class="controls controls-row">
									<input type="text" class="input-block-level" name="benefit" {{bindAttr value="benefit"}}/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="role">Rolle</label>
								<div class="controls controls-row">
									<select name="role">
										<option>-</option>
										<option>Administrator</option>
										<option>Premium User</option>
										<option>Free User</option>
									</select>
									<button class="btn">Rollen Verwalten</button>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="description">Beschreibung</label>
								<div class="controls controls-row">
									<textarea class="input-block-level" rows="3" name="description">{{unbound description}}</textarea>
								</div>
							</div>
							<hr/>
							<div class="control-group">
								<label class="control-label" for="action">Aktion</label>
								<div class="controls controls-row">
									<select name="action">
										<option>Keine</option>
										<option>Löschen</option>
										<option>Epic B zuweisen</option>
										<option>Epic C zuweisen</option>
									</select>
								</div>
							</div>
							<div class="pull-right">
								<button class="btn" {{action "saveButton" this}}>Speichern</button> 
								<button class="btn btn-primary">Speichern &amp; Neue Story anlegen</button>
							</div>
							<div class="clearfix"/>
						</form>
					  </section>
  				  {{/each}}
				</div>
			</div>
		</div>

{{view Ember.Select 
	contentBinding = "App.RolesController"
	selectionBinding="role"
	optionLabelPath="content.name"
    optionValuePath="content.id"
}}

{{#view App.BootstrapControl inputId="role" label="Rolle"}}
								{{view Ember.TextField valueBinding="role.name"}}
								<button {{action "test"}}>Edit!</button>
						{{/view}}

<div class="row-fluid">
							Verschieben nach Epic								
							{{view App.Select
								contentBinding="controller.epics"
        						selectionBinding="epic"
								valueBinding="epic_id"}}								
							<button type="submit" class="btn btn-primary pull-right">Speichern</button>
						</div>


testProperty: function(){
		console.info("Test prop fired!");
		if(null != this.get('selectedRoleIds'))
		{
			return this.get('selectedRoleIds')[0].id
		}
		else
		{
			return null;
		}
	}.property('selectedRoleIds')

<div class="span4">
				  <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="200">
					{{#each user_story in stories}}
					<li><a href="#">{{user_story.name}}</a></li>
					{{/each}}
				  </ul>
				</div>
		
	</script>