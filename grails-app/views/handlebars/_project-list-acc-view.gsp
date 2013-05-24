<script type="text/x-handlebars" data-template-name="project-list_item">
	<div class="accordion-heading">
		<div class="row-fluid">
			<a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href" }}>
				{{item.name}}
			</a>
			{{#linkTo 'project' item class="btn btn-secondary pull-right"}}{{item.name}}{{/linkTo}}
		</div>
	</div>
	<div {{bindAttr id='item.id' }} class="accordion-body collapse in">
		<div class="accordion-inner">			
			{{#view Ember.TextField valueBinding="itme.description" label="Description"}}			
		</div>
	</div>
</script>