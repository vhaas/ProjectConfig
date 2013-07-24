<g:render template="/handlebars/milestone-view"></g:render>
<g:render template="/handlebars/unselected-userstories-view"></g:render>
<script type="text/x-handlebars" data-template-name="roadmap">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li>{{#linkTo projects}}Projects{{/linkTo}}<span class="divider">/</span></li>
					<li>{{#linkTo project.index project}}{{project.name}}{{/linkTo}}<span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span3">
				<form class="form-vertical">
					<section>
						<form class="form-vertical" {{action save on="submit"}}>
							{{#view App.BootstrapControl inputId="name" label="Name"}}
								{{view Ember.TextField valueBinding="name"}}
							{{/view}}
							{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
								{{view Ember.TextArea valueBinding="description"}}
							{{/view}}
							<div>
								<button type="submit" class="btn" size="1">Save changes</button>
							</div>
								<button class="btn" {{action "createNewRoadMap" target="view"}}>Create new RoadMap</button>
							<div class="clearfix"/>
						</form>
					</section>
					<section>
						<div>
							<button class="btn" {{action "createNewMileStone" target="view"}}>Create new MileStone</button>
						</div>
						<div>
							Number of Milestones: {{mileStones.length}}
						</div>
					</section>
					<section style="display: block; overflow-y: scroll">
						{{outlet unselected-user-stories}}
					</section>
				</form>
			</div>
			<div class="span9">
				{{outlet mileStones}}
			</div>
		</div>
</script>