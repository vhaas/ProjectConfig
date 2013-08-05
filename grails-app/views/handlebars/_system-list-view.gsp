<script type="text/x-handlebars" data-template-name="system-list">
	<section>
		<table class="table">
			{{#each item in controller.arrangedContent}}
				<tr>
					<td>
						{{#linkTo system item}} {{item.name}} {{/linkTo}}
					</td>
					<td>
						<button class="btn btn-mini pull-right"{{action "selectSystem" item.id}}><i class="icon-wrench"></i></button>
					</td>
				</tr>
			{{/each}}
		</table>
	</section>
</script>