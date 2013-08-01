<script type="text/x-handlebars" data-template-name="confirm-delete-modal">
	<div class="modal">
  		<div class="modal-header">
    		{{unbound name}}
  		</div>
  		<div class="modal-body">
    		Are you sure you want to delete this?
  		</div>
  		<div class="modal-footer">
    		<button class="btn btn-default" {{action confirm}}>Yes delete this</button>
    		<button class="btn btn-default" {{action close}}>Cancel</button>
  		</div>
	</div>
</script>