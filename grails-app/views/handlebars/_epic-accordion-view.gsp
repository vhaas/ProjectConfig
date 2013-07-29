<script type="text/x-handlebars" data-template-name="epic-accordion">
	<section style="display: block; height: 50em; overflow-y: scroll">
		<div class="accordion" id="accordion2">
			{{#each item in controller}}
				{{view App.EpicAccordionView contextBinding='item'}}
			{{/each}}
		</div>
	</section>
</script>