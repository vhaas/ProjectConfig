<script type="text/x-handlebars" data-template-name="epic-acc-item">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href"}}>
				{{item.name}}
				<button class="btn btn-mini pull-right"{{action "select" item}}><i class="icon-arrow-right"></i></button>
            </a>
		</div>
		<div {{bindAttr id='item.id' }} class="accordion-body collapse in">
			<div class="accordion-inner">
				<table class="table table-bordered">
					{{#each item.userStories}}
						<tr>
							<td>
								{{name}}
							</td>
						</tr>
					{{/each}}
				</table>
            </div>
		</div>
	</div>
</script>