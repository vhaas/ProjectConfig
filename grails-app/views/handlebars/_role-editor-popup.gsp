<script type="text/x-handlebars" data-template-name="roleEditor">
	<section>
		<h4>
			{{name}}
				{{#if isDirty}}
					<i class="icon-exclamation-sign"></i>
				{{/if}}
		</h4>
		<form class="form-horizontal" {{action save on="submit"}}>
			{{#view App.BootstrapControl inputId="name" label="Name"}}
				{{view Ember.TextField valueBinding="name"}}
			{{/view}}
			{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
				{{view Ember.TextArea valueBinding="description"}}
			{{/view}}
			<div class="row-fluid">
				<button type="submit" class="btn btn-primary pull-right">Speichern</button>
				<button class="btn btn-secondary pull-right" {{action "create"}}>Neue Rolle anlegen</button>
			</div>
			<div class="clearfix"/>
		</form>
	</section>
</script>