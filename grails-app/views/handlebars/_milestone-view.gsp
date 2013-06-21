<script type="text/x-handlebars" data-template-name="milestone">
	<form class="form-vertical" style="height: 50em; overflow-x: scroll"">
	{{#each milestone in controller}}
		<div class="span3">
			<form>
				<section>
					<h5>{{milestone.name}}</h5>
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
					{{#view App.BootstrapControl inputId="userStories" label="Unselected User Stories"}}
						{{view App.Select
							contentBinding="App.SelectMileStoneUserStoriesController"
							selectionBinding="userStories"
							valueBinding="aaa"
							prompt="Please select a userstory"}}
					{{/view}}
				</section>
			<form>
		</div>
	{{/each}}
	</form>
	<button type="button" class="close" {{action "select" roadmap.id}}>&times;</button>
</script>