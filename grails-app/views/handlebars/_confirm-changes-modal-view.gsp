<script type="text/x-handlebars" data-template-name="confirm-changes-modal">
	<div class="modal">
		<div class="modal-header">
	    	{{unbound name}}
	  	</div>
	  	<div class="modal-body">
	    Do you want your changes to be saved?
	  	</div>
	  	<div class="modal-footer">
		    <button class="btn btn-default" {{action confirmSaveChanges target="controller"}}>Save changes</button>
	    	<button class="btn btn-default" {{action confirmRollbackChanges target="controller"}}>Discard changes</button>
	  	</div>
	</div>
</script>