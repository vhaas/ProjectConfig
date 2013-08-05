

<script type="text/x-handlebars" data-template-name="system-change-input">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li>{{#linkTo projects}}Projects{{/linkTo}}<span class="divider">/</span></li>
					<li>{{#linkTo project.index project}}{{project.name}}{{/linkTo}}<span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span3">
				<div>
					{{outlet userstory-table}}
				</div>
				<div>
					{{outlet system-table}}
				</div>
			</div>
			<div class="span9">
				<div>
					{{outlet system-changes}}
				</div>
			<div>
		</div>
</script>