<script type="text/x-handlebars" data-template-name="modal2">
	<div class="modal">
  		<div class="modal-header">
    		{{#if isNew}}
      			Create Widget
			{{else}}
      			Editing: {{unbound name}}
			{{/if}}
		</div>
  		<form {{action save on="submit"}}>
    		<div class="modal-body">
      			<div {{bindAttr class="errors.name:error :field"}}>
        			<label>Name</label>
    				{{view Ember.TextField valueBinding="name"}}
        				{{#each msg in errors.name}}{{msg}}{{/each}}
      			</div>
    		</div>
			<div class="modal-footer">
      			<button type="submit" {{bindAttr disabled="shouldDisableSubmit"}}>Save</button>
      			<button {{action close}}>Cancel</button>
      			{{#if isSaving}}
        			<img src="{{unbound App.AJAX_LOADER_IMG}}" alt="Loading..." class="ajax-loader">
        			Saving...
      			{{/if}}
    		</div>
  		</form>
	</div>
</script>