<script type="text/x-handlebars" data-template-name="basic-modal">
	<div class="modal soft">
  		<div class="modal-header">
    		{{#if isNew}}
      			Create new {{view.className}}
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
    				<label>Description</label>
    				{{view Ember.TextArea valueBinding="description"}}
        				{{#each msg in errors.name}}{{msg}}{{/each}}
      			</div>
    		</div>
			<div class="modal-footer">
      			<button class="btn" style="width:6em" type="submit" {{bindAttr disabled="shouldDisableSubmit"}}>Save</button>
      			<button class="btn" style="width:6em" {{action close}}>Cancel</button>
      			{{#if isSaving}}
        			<img src="{{unbound App.AJAX_LOADER_IMG}}" alt="Loading..." class="ajax-loader">
        			Saving...
      			{{/if}}
    		</div>
  		</form>
	</div>
</script>