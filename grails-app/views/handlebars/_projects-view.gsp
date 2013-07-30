<g:render template="/handlebars/project-acc-item-view"></g:render>
<g:render template="/handlebars/project-view"></g:render>
<script type="text/x-handlebars" data-template-name="projects">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li class="active">Projects</li>
				</ul>
			</div>
		<!-- Project -->
			<section style="display: block; height: 50em; overflow-y: scroll">
				<div class="accordion" id="accordion1">
					{{#each item in controller}}
						{{view App.ProjectListView contextBinding='item'}}
					{{/each}}
				</div>
			</section>
		</div>
</script>