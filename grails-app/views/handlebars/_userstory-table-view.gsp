<script type="text/x-handlebars" data-template-name="userstory-table">
	<section>
		<table class="table table-hover">
			<thead>
      			<tr>
        	 		<th>User Stories</th>
         			<th>System Change count</th>
      			</tr>
   			</thead>
   			<tbody>
				{{#each item in controller}}
					{{#linkTo 'systemchange' item tagName="tr" activeClass="info"}}
						<td>
							{{item.name}}
						</td>
						<td>
							{{item.systemChanges.length}}
						</td>
					{{/linkTo}}
				{{/each}}
			</tbody>
		</table>
	</section>
</script>