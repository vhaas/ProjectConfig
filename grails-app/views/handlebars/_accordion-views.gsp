<script type="text/x-handlebars" data-template-name="acc_item">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href" }}>
				{{item.name}}
            </a>
		</div>
		<div {{bindAttr id='item.id' }} class="accordion-body collapse in">
			<div class="accordion-inner">
				{{view Ember.TextArea valueBinding="description" label="Description"}}
				<button class="btn btn-secondary pull-right" {{action "select" id}}>select</button>
            </div>
		</div>
</script>