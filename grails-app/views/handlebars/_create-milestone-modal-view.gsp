<script type="text/x-handlebars" data-template-name="newMileStone">
	<section>
		<h4>
			{{name}}
				{{#if isDirty}}
					<i class="icon-exclamation-sign"></i>
				{{/if}}
		</h4>
		<form class="form-horizontal" {{action saveMilestone on="submit" target="view"}}>
			{{#view App.BootstrapControl inputId="name" label="Name"}}
				{{view Ember.TextField valueBinding="name"}}
			{{/view}}
			{{#view App.BootstrapControl inputId="description" label="Description"}}
				{{view Ember.TextArea valueBinding="description"}}
			{{/view}}
			<div class="row-fluid">
				<button type="submit" class="btn btn-primary pull-right"{{bindAttr disabled="view.isNotDirty"}}>Save</button>
			</div>
			<div class="clearfix"/>
		</form>
	</section>
</script>