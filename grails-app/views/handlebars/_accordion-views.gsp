<script type="text/x-handlebars" data-template-name="acc_item">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href"}}>
				{{item.name}}
				<button class="close pull-right" {{action "select" id}}>&rArr;</button>
            </a>
		</div>
		<div {{bindAttr id='item.id' }} class="accordion-body collapse in">
			<div class="accordion-inner">
				<div class="row-fluid">
					<span class="label">Description</span>
					<div class="well">
						{{description}}
					</div>
				</div>
            </div>
		</div>
	</div>
</script>