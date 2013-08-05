<script type="text/x-handlebars" data-template-name="system-table">
	<section>
		<table class="table table-striped">
			<thead>
      			<tr>
        	 		<th>Systems</th>
         			<th>System Change count</th>
      			</tr>
   			</thead>
   			<tbody>
				{{#each item in controller.filteredContent}}
					<tr>
						<td>
							{{item.name}}
						</td>
						<td>
							{{item.systemChanges.length}}
						</td>
					</tr>
				{{/each}}
			</tbody>
		</table>
	</section>
</script>