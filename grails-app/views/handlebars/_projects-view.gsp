<script type="text/x-handlebars" data-template-name="projects">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>					
					<li class="active">Projects</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="span4">
				</div>
			<div class="span8">			
		<!-- Project -->
			{{#each project in  controller}}
				<section>					
					<h4>
						{{name}}
					</h4>					
					<form class="form-horizontal">
						{{#view App.BootstrapControl inputId="name" label="Name"}}															
							{{view Ember.TextField disabled="" valueBinding="name"}}							
						{{/view}}
						{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
							{{view Ember.TextArea disabled="" valueBinding="description"}}
						{{/view}}
						<button class="btn btn-secondary pull-right" {{action "select" id}}>Select</button>
						<div class="clearfix"/>						
					</form>
				</section>
			{{/each}}
			</div>
		</div>
</script>