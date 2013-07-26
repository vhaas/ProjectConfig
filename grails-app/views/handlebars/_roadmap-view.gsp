<g:render template="/handlebars/milestone-view"></g:render>
<g:render template="/handlebars/unselected-userstories-view"></g:render>
<g:render template="/handlebars/popup-view"></g:render>
<g:render template="/handlebars/roadmap-modal-view"></g:render>
<g:render template="/handlebars/milestone-modal-view"></g:render>
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
						<h4>
							{{name}}
							{{#if isDirty}}
								<i class="icon-exclamation-sign"></i>
							{{/if}}
						</h4>
						<form class="form-vertical" {{action save on="submit"}}>
							{{#view App.BootstrapControl inputId="name" label="Name"}}
								{{view Ember.TextField valueBinding="name"}}
							{{/view}}
							{{#view App.BootstrapControl inputId="description" label="Description"}}
								{{view Ember.TextArea valueBinding="description"}}
							{{/view}}
							<button type="submit" class="btn btn-primary pull-left" style="width:12em" {{bindAttr disabled="isNotDirty"}}>Save changes</button>
							<div class="clearfix"/>
						</form>
						<button class="btn pull-right" style="width:12em" {{action "createRoadmap" this}}>Create new RoadMap</button>
					</section>
					<section style="height:3em">
						<div>
							<button class="btn pull-right" style="width:12em" {{action "createMilestone" this}}>Create new MileStone</button>
						</div>
						<div>
							Number of Milestones: {{mileStones.length}}
						</div>
					</section>
					<section style="height:22em;display: block; overflow: auto">
						{{outlet unselected-user-stories}}
					</section>
				</form>
			</div>
			<div class="span9">
				{{outlet mileStones}}
			</div>
		</div>
</script>