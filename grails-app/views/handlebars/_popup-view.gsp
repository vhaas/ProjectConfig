<script type="text/x-handlebars" data-template-name="modal">
    <div class="modal-overlay"></div>
	
    <div class="modal soft">		
		{{yield}}
		<button class="btn space-big soft" {{action "closeModal" target="view"}}>Close Dialog</button>
    </div>
  </script>