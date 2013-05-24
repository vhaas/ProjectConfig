<script type="text/x-handlebars" data-template-name="projects">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>					
					<li class="active">Projects</li>
				</ul>
			</div>						
		<!-- Project -->
			{{#each project in  controller}}
				<section>					
					<h4>
						{{name}}
					</h4>					
					<form class="form-horizontal">
						{{#view App.BootstrapControl inputId="name" label="Name"}}															
							{{view Ember.TextField disabled="" valueBinding="name"}}							
						{{/view}}
						{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
							{{view Ember.TextArea disabled="" valueBinding="description"}}
						{{/view}}
						<button class="btn btn-secondary pull-right" {{action "select" id}}>Select</button>
						<div class="clearfix"/>						
					</form>
				</section>
			{{/each}}
			</div>
		</div>
</script>

<div class="accordion-heading">			
            <a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href" }}>              
			 	{{item.name}}
            </a>
			{{#linkTo 'epic' item class="btn btn-secondary pull-righ"}}{{item.name}}{{/linkTo}}						
          </div>
          <div {{bindAttr id='item.id' }} class="accordion-body collapse in">
            <div class="accordion-inner">
				{{#each item.userStories}}
        			<ul>{{name}}</ul>					
				{{/each}}				
            </div>
          </div>
          
          <div class="span4">
					<section>
						<div class="accordion" id="accordion2">
	      					{{#each item in epics}}
       							{{view App.AccItemView contextBinding='item'}}
      						{{/each}}							
    					</div>
					</section>
				</div>