<script type="text/x-handlebars" data-template-name="add-system-change-modal">
	<div class="modal">
  		<div class="modal-header">
    		Add System Change
		</div>
  		<form {{action save on="submit"}}>
    		<div class="modal-body">
      			<div>
        			<button class="btn pull-left">Create new System Change</button>
      			</div>
      			<div class="pull-right">
      				<label>
      					Filter System
  					</label>
					{{view App.SelectFilterSystem
						contentBinding="App.FilterSystemController.arrangedContent"
						selectionBinding="App.SystemChangesModalController.selectedFilter"
					}}
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