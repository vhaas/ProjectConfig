<script type="text/x-handlebars" data-template-name="epic-list">
	<section>
		<table class="table">
			{{#each item in controller}}
				<tr>
					<td>
						{{#linkTo roadmap item}} {{item.name}} {{/linkTo}}
					</td>
					<td>
						<button class="btn btn-mini pull-right"{{action "selectEpic" item.id}}><i class="icon-wrench"></i></button>
					</td>
				</tr>
			{{/each}}
		</table>
	</section>
</script>