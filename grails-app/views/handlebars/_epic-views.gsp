<script type="text/x-handlebars" data-template-name="epic">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">{{project.name}}</a> <span class="divider">/</span></li>
					<li><a href="#">User Story Editor</a> <span class="divider">/</span></li>
					<li class="active">{{name}}</li>
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
					<h4>
					{{#if isNotDirty}}						
						<i class="icon-exclamation-sign"></i>
					{{/if}}					
					{{name}}</h4>
					<form class="form-horizontal" {{action save on="submit"}}>
						{{#view App.BootstrapControl inputId="name" label="Name"}}
								{{view Ember.TextField valueBinding="name"}}
						{{/view}}
						{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
								{{view Ember.TextArea valueBinding="description"}}
						{{/view}}
						<button type="submit" class="btn btn-primary pull-right">Speichern</button>
						<div class="clearfix"/>
					</form>
				  </section>
			</div>
		</div>
</script>
