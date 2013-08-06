<script type="text/x-handlebars" data-template-name="systemchange-list">
	<form class="form-vertical" style="height: 50em; overflow-x:auto">
	<table class="table">
		<tr class="pull-left">
			{{#each item in controller}}
				<td>
					<div>
						<section style="height:45em;width:20em">
							<form class="form-horizontal" {{action save on="submit"}}>
								<h5>
									{{item.name}}
								</h5>
								<div class="pull-right">
									<button type="submit" class="btn btn-primary">Save</button>
									<button class="btn btn-secondary" {{action "discardChanges"}}>Discard</button>
									{{#view App.BootstrapControl inputId="controller.isEditable" label="Edit System Change"}}
										{{view Ember.Checkbox disabledBinding="controller.isDisabled" checkedBinding="controller.isEditable"}}
									{{/view}}
								</div>
								<div>
									{{#view App.BootstrapControl inputId="system" label="System"}}
										{{view App.SystemSelect
											disabledBinding="controller.isDisabled"
											contentBinding="controller.controllers.roles.arrangedContent"
											selectionBinding="system"
											valueBinding="system_id"
											prompt="Select System"}}
										<button class="btn btn-secondary" {{action "createSystem" this}}{{bindAttr disabled="controller.isDisabled"}}>add System</button>
									{{/view}}
								</div>
								<div>
									{{#view App.BootstrapControl inputId="adaptionType" label="Adaption Type"}}
										{{view App.AdaptionTypeSelect
											disabledBinding="controller.isDisabled"
											contentBinding="controller.controllers.roles.arrangedContent"
											selectionBinding="system"
											valueBinding="system_id"
											prompt="Select Adaption Type"}}
										<button class="btn btn-secondary" {{action "createRole" this}}{{bindAttr disabled="controller.isDisabled"}}>add Adaption Type</button>
									{{/view}}
								</div>
								<div>
									{{#view App.BootstrapControl inputId="adaptionAspect" label="Adaption Aspect"}}
										{{view Ember.TextArea disabledBinding="controller.isDisabled" valueBinding="adaptionAspect"}}
									{{/view}}
								</div>


















								<table class="table">
								{{#each story in milestone.userStories}}
									<tr>
										<td>
											{{story.name}}
										</td>
										<td>
											<button type="button" class="close" {{action "removeUserstory" story}}>&times;</button>
										</td>
									</tr>
								{{/each}}
								</table>
								{{#view App.BootstrapControl inputId="milestone.userStories" label="Unassigned User Stories"}}
									{{view App.Select
										contentBinding="controller.controllers.userstorylist.filteredContent"
										selectionBinding="controller.selectedUserStory"
										valueBinding="milestone.userStories_id"
										}}
								{{/view}}
							</form>
						</section>
					</div>
				</td>
			{{/each}}
		</tr>
	</table>
	</form>
</script>