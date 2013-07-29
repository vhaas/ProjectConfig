<script type="text/x-handlebars" data-template-name="epic-acc-item">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href"}}>
				{{item.name}}
				<button class="btn btn-mini pull-right"{{action "select" item.id}}><i class="icon-arrow-right"></i></button>
            </a>
		</div>
		<div {{bindAttr id='item.id' }} class="accordion-body collapse in">
			<div class="accordion-inner">
				<div class="row-fluid">
					<span class="label">Description</span>
					<div class="well">
						{{item.description}}
					</div>
				</div>
            </div>
		</div>
	</div>
</script>