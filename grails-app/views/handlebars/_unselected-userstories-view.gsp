<script type="text/x-handlebars" data-template-name="unselected-userstories">
	<table class="table">
		{{#each item in controller}}
			<tr>
				<td>
					{{item.name}}
				</td>
			</tr>
		{{/each}}
	</table>
</script>