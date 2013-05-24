<script type="text/x-handlebars" data-template-name="roadmap">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#/projects">Projects</a> <span class="divider">/</span></li>
					<li><a href="#">{{project.name}}</a> <span class="divider">/</span></li>
					<li><a href="#">RoadMaps</a> <span class="divider">/</span></li>
					<li><a href="#">RoadMap {{name}}</a> <span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="span3">					
					<form class="form-vertikal">
						<title>Unselected User Stories</title>
						{{#each userStories}}
							<div class="well well-small">
								{{name}}
							</div>
						{{/each}}
					</form>
					<form class="form-vertikal" {{action save on="submit"}}>
						{{#view App.BootstrapControl inputId="name" label="Name"}}
							{{view Ember.TextField valueBinding="name"}}
						{{/view}}
						{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
							{{view Ember.TextArea valueBinding="description"}}
						{{/view}}
						<button type="submit" class="btn">Speichern</button>
						<button class="btn" {{action "createNewRoadMap" target="view"}}>Neue RoadMap anlegen</button>
						<div class="clearfix"/>
					</form>
				</div>
				<section>				
					{{#each mileStones}}
						<div class="column-fluid">
							<div class="span2">
								<form class="form-vertikal">
									{{#view App.BootstrapControl inputId="name" label="Name"}}
										{{view Ember.TextField valueBinding="name"}}
									{{/view}}
									{{#each userStories}}
										<div class="well well-small">
											{{name}}
											<button type="button" class="close">&times;</button>
										</div>										
									{{/each}}
								</form>
							</div>
						</div>
					{{/each}}				
				</section>
			</div>
		</div>	
</script>