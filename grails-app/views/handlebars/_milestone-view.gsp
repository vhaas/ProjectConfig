<script type="text/x-handlebars" data-template-name="milestone">
	<form class="form-vertical" style="height: 50em; overflow-x: scroll"">
	{{#each milestone in controller}}
		<div class="span3">
			<form>
				<section>
					<h5>
						{{milestone.oderId}}
						{{#if nameIsEmpty}}
							{{milestone.orderId}}
						{{else}}
							{{milestone.name}}
						{{/if}}
					</h5>
					<div class="well well-small">{{milestone.description}}</div>
					<table class="table">
					{{#each story in milestone.userStories}}
						<tr>
							<td>
								{{story.name}}
							</td>
						</tr>
					{{/each}}
					</table>
					<button type="button" class="btn" {{action "doda" target="controller"}}>&times;</button>
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