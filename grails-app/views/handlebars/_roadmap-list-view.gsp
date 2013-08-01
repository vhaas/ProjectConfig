<script type="text/x-handlebars" data-template-name="roadmap-list">
	<section>
		<table class="table">
			{{#each item in controller.arrangedContent}}
				<tr>
					<td>
						{{#linkTo roadmap item}} {{item.name}} {{/linkTo}}
					</td>
					<td>
						<button class="btn btn-mini pull-right"{{action "selectRoadmap" item.id}}><i class="icon-wrench"></i></button>
					</td>
				</tr>
			{{/each}}
		</table>
	</section>
</script>