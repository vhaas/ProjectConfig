<g:render template="/handlebars/accordion-views"></g:render>
<script type="text/x-handlebars" data-template-name="projects">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>					
					<li class="active">Projects</li>
				</ul>
			</div>			
		<!-- Project -->
			<div class="accordion" id="accordion2">
				<section style="display: block; height: 50em; overflow: scroll">
					{{#each item in controller}}
						{{view App.ProjectListView contextBinding='item'}}
					{{/each}}
				</section>
			</div>			
		</div>
</script>