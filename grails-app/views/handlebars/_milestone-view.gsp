<script type="text/x-handlebars" data-template-name="milestone">
	<form class="form-vertical" style="height: 50em; overflow-x: scroll"">
	{{#each milestone in controller}}
		<div class="span3">
			<form>
				<section>
					<h5>{{milestone.name}}</h5>
					<div class="well well-small">{{description}}</div>
				</section>
			<form>
		</div>
	{{/each}}
	</form>
</script>