<script type="text/x-handlebars" data-template-name="milestone">
	<form class="form-vertical" style="height: 50em; overflow-x:auto">
	<table class="table">
		<tr class="pull-left">
			{{#each milestone in controller}}
				<td>
					<div>
						<section style="height:45em;width:20em">
							<h5>
								{{#if milestone.nameIsEmpty}}
									{{milestone.orderId}}
								{{else}}
									{{milestone.name}}
								{{/if}}
								<button class="btn btn-mini pull-right"{{action "deleteMilestone" milestone}}><i class="icon-trash"></i></button>
								<button class="btn btn-mini pull-right"{{action "editMilestone" milestone}}><i class="icon-wrench"></i></button>
							</h5>
							<div class="well well-small">{{milestone.description}}</div>
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
						</section>
					</div>
				</td>
			{{/each}}
		</tr>
	</table>
	</form>
</script>