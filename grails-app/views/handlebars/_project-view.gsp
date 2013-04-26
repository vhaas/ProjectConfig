<script type="text/x-handlebars" data-template-name="project">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">Projects</a> <span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="span4">
				</div>
			<div class="span8">
		<!-- Project -->			
				<section>
					<h4>
						{{name}}						
					</h4>
					<form class="form-horizontal" {{action save on="submit"}}>
						{{#view App.BootstrapControl inputId="name" label="Name"}}
								{{view Ember.TextField disabledBinding="controller.disabledProject" valueBinding="name"}}
						{{/view}}
						{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
								{{view Ember.TextArea disabledBinding="controller.disabledProject" valueBinding="description"}}
						{{/view}}
						{{#unless isDirty}}
							<button class="btn btn-secondary pull-right" {{action "enableProject" target="view"}}>Edit</button>
							<button class="btn btn-secondary pull-right" {{action "selectProject" target="view"}}>Select</button>
						{{else}}
							<button type="submit" class="btn btn-primary pull-right">Speichern</button>
						{{/unless}}
						<div class="clearfix"/>						
					</form>
				</section>
			</div>
		</div>
</script>