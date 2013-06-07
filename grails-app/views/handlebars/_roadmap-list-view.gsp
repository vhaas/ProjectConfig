<g:render template="/handlebars/accordion-roadmap-views"></g:render>
<script type="text/x-handlebars" data-template-name="roadmap-list">
	<section>
		<div class="accordion" id="accordion2">
			{{#each item in controllers.roadmaplist}}
				{{view App.RoadmapListView contextBinding='item'}}
			{{/each}}
		</div>
	</section>
</script>