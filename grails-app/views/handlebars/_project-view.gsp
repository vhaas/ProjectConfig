<g:render template="/handlebars/roadmap-list-view"></g:render>
<g:render template="/handlebars/epic-list-view"></g:render>
<g:render template="/handlebars/system-list-view"></g:render>
<g:render template="/handlebars/roadmap-view"></g:render>
<g:render template="/handlebars/epic-view"></g:render>
<script type="text/x-handlebars" data-template-name="project">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li>{{#linkTo projects}}Projects{{/linkTo}}<span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>
			<div>
				<form style="display: block; height: 50em">
					<div class="row-fluid">
						<div class="span6">
							<section class="form-horizontal" style="height: 20em">
								<h4>{{name}}</h4>
								Description
								<div class="well">{{description}}</div>
							</section>
						</div>
						<div class="span6">
							<section class="form-horizontal" style="height: 20em; text-align: center">
								Placeholder
							</section>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span4">
							<h5>Roadmaps</h5>
							{{outlet roadmap-list}}
						</div>
						<div class="span4">
							<h5>Epics</h5>
							{{outlet epic-list}}
						</div>
						<div class="span4">
							<h5>Systems</h5>
							{{outlet system-list}}
						</div>
					</div>
				</form>
			</div>
		</div>
</script>