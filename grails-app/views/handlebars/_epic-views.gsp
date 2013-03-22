<g:render template="/handlebars/popup-view"></g:render>
<script type="text/x-handlebars" data-template-name="epic">
		<div class="container-fluid" data-spy="scroll" data-target=".nav-tabs">
			<div class="row-fluid">
				<ul class="breadcrumb span12">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">{{project.name}}</a> <span class="divider">/</span></li>
					<li><a href="#">User Story Editor</a> <span class="divider">/</span></li>
					<li class="active">{{name}}</li>
				</ul>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<section>
						<div class="accordion" id="accordion2">
	      					{{#each item in epics}}
       							{{view App.AccItemView contextBinding='item'}}
      						{{/each}}
    					</div>
					</section>
				</div>				
				<div class="span8">										
			<!-- Epic -->
				  <section>
					<h4>
						{{name}}
						{{#if isDirty}}
							<i class="icon-exclamation-sign"></i>
						{{/if}}
					</h4>
					<form class="form-horizontal" {{action save on="submit"}}>
						{{#view App.BootstrapControl inputId="name" label="Name"}}
								{{view Ember.TextField valueBinding="name"}}
						{{/view}}
						{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
								{{view Ember.TextArea valueBinding="description"}}
						{{/view}}
						<button type="submit" class="btn btn-primary pull-right">Speichern</button>
						<div class="clearfix"/>						
					</form>
				  </section>
			<!-- User Stories -->
				{{#each userStories}}
				<section>
					<h4>
						{{name}}
						{{#if isDirty}}
							<i class="icon-exclamation-sign"></i>
						{{/if}}
					</h4>
					<form class="form-horizontal" {{action save on="submit"}}>
						{{#view App.BootstrapControl inputId="name" label="Name"}}
								{{view Ember.TextField valueBinding="name"}}
						{{/view}}
						{{#view App.BootstrapControl inputId="goal" label="Ziel"}}
								{{view Ember.TextField valueBinding="goal"}}
						{{/view}}
						{{#view App.BootstrapControl inputId="benefit" label="Nutzen"}}
								{{view Ember.TextField valueBinding="benefit"}}
						{{/view}}
						{{#view App.BootstrapControl inputId="description" label="Beschreibung"}}
								{{view Ember.TextArea valueBinding="description"}}
						{{/view}}						
						{{#view App.BootstrapControl inputId="role" label="Rolle"}}
								{{view Ember.TextField valueBinding="role.name"}}
								<button {{action "test"}}>Edit!</button>
						{{/view}}
						<button type="submit" class="btn btn-primary pull-right">Speichern</button>
						<div class="clearfix"/>
					</form>
				</section>
				{{/each}}
			</div>
		</div>
</script>

<script type="text/x-handlebars" data-template-name="acc_item">
          <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href" }}>
              {{item.name}}
            </a>
          </div>
          <div {{bindAttr id='item.id' }} class="accordion-body collapse in">
            <div class="accordion-inner">				
				{{#each item.userStories}}				
        			<ul>{{name}}</ul>
				{{/each}}				      			             
            </div>
          </div>
</script>
