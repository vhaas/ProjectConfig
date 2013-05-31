<g:render template="/handlebars/accordion-views"></g:render>
<script type="text/x-handlebars" data-template-name="roadmap-list">
		<div class="accordion" id="accordion2">
			{{#each item in controller}}
				{{view App.RoadmapListView contextBinding='item'}}
			{{/each}}
		</div>
</script>