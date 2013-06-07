<script type="text/x-handlebars" data-template-name="roadmap-list">
	<section>		
		{{#each item in controller}}		
			<ul>
				<div class="row-fluid centre">
					{{#linkTo roadmap item}} {{item.name}} {{/linkTo}}
					<button class="btn btn-mini pull-right"{{action "select" item.id}}><i class="icon-wrench"></i></button>					
				</div>
			</ul>
		{{/each}}
	</section>
</script>