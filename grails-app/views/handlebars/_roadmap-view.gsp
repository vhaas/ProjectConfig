<script type="text/x-handlebars" data-template-name="roadmap">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li>{{#linkTo projects}}Projects{{/linkTo}}<span class="divider">/</span></li>
					<li>{{#linkTo project project}}{{project.name}}{{/linkTo}}<span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>			
		</div>				
</script>