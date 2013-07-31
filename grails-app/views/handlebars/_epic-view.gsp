<g:render template="/handlebars/epic-accordion-view"></g:render>
<g:render template="/handlebars/epic-acc-item-view"></g:render>
<g:render template="/handlebars/userstory-list-view"></g:render>
<g:render template="/handlebars/basic-modal-view"></g:render>
<g:render template="/handlebars/confirm-changes-modal-view"></g:render>
<script type="text/x-handlebars" data-template-name="epic">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li>{{#linkTo projects}}Projects{{/linkTo}}<span class="divider">/</span></li>
					<li>{{#linkTo project.index project}}{{project.name}}{{/linkTo}}<span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>

			</div>
			<div class="row-fluid">
				<div class="span4">
					{{outlet epic-accordion}}
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
								{{view Ember.TextField disabledBinding="controller.isDisabled" valueBinding="name"}}
							{{/view}}
							{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
								{{view Ember.TextArea disabledBinding="controller.isDisabled" valueBinding="description"}}
							{{/view}}
							{{#unless isDirty}}
								{{#if controller.isDisabled}}
									<button class="btn btn-secondary pull-right" {{action "setEnabled" this target="controller"}}>Enable</button>
								{{else}}
									<button class="btn btn-secondary pull-right" {{action "setDisabled" this target="controller"}}>Disable</button>
								{{/if}}
								<button class="btn btn-secondary pull-left" {{action "create" target="controller"}}>Create new Epic</button>
								<button class="btn btn-secondary pull-left" {{action "createUserstory" this }}>Create new User Story</button>
							{{else}}
								<button type="submit" class="btn btn-primary pull-right">Save</button>
							{{/unless}}
							<div class="clearfix"/>
						</form>
					</section>
					{{outlet userStories}}
				</div>
			</div>
		</div>
</script>