<script type="text/x-handlebars" data-template-name="drag">
	<b>Available Products</b>
    <br /><br />
    {{#each product in model}}
		{{#view App.ProductView contentBinding="product"}}
        	{{view.content.name}}
		{{/view}}<br />
    {{/each}}
    <hr />

    {{#view App.ProductDropTarget dragContextBinding="currentDragItem"}}
    	Shopping Cart
    	<div style="height: 20px">{{helpText}}</div>
    {{/view}}
    <br />
    {{#each cart in productsInCart}}
		{{#view App.ProductView contentBinding="cart"}}
			{{view.content.name}}
		{{/view}}<br />
    {{/each}}
</script>