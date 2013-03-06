<script type="text/x-handlebars" data-template-name="bootstrap-control">
	<label class="control-label" {{bindAttr for="view.inputId"}}">{{view.label}}</label>
	<div class="controls controls-row">
		{{yield}}
	</div>
</script>