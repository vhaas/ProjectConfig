<script type="text/x-handlebars" data-template-name="acc_item">
          <div class="accordion-heading">			
            <a class="accordion-toggle" data-toggle="collapse"  {{bindAttr href="item.href" }}>              
			 	{{#linkTo 'epic' item}}{{item.name}}{{/linkTo}}
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