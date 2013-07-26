<script type="text/x-handlebars" data-template-name="milestone">
	<form class="form-vertical" style="height: 50em; overflow-x: scroll"">
	{{#each milestone in controller}}
		<div class="span3">
			<form>
				<section>
					<h5>
						{{milestone.orderid}}
						{{#if nameIsEmpty}}
							{{milestone.orderid}}
						{{else}}
							{{milestone.name}}
						{{/if}}
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
								<button type="button" class="close" {{action "select" story.id}}>&times;</button>
							</td>
						</tr>
					{{/each}}
					</table>
					{{#view App.BootstrapControl inputId="milestone.userStories" label="Unassigned User Stories"}}
						{{view App.Select
							contentBinding="controller.controllers.userstorylist.content"
							selectionBinding="milestone.userStories"
							valueBinding="milestone.userStories_id"
							}}
					{{/view}}
				</section>
			<form>
		</div>
	{{/each}}
	</form>
	<button type="button" class="close" {{action "select" roadmap.id}}>&times;</button>
</script>