//***Drag & Drop***//

App.DragRoute = Ember.Route.extend({
	model : function() {
		return [ App.Product.create({
			name : "MacBook Pro",
			isAdded : false
		}), App.Product.create({
			name : "iPhone",
			isAdded : false
		}), App.Product.create({
			name : "iPad",
			isAdded : true
		}), App.Product.create({
			name : "iTV",
			isAdded : false
		}) ];
	},
	renderTemplate : function() {
		this.render('drag', {
			into : 'application',
			controller : 'drag'
		});
	})
});

App.Product = Ember.Object.extend({
	name : null,
	isAdded : null
});

DragNDrop = Ember.Namespace.create();

DragNDrop.cancel = function(event) {
	event.preventDefault();
	return false;
};

DragNDrop.Draggable = Ember.Mixin.create({
	attributeBindings : "draggable",
	draggable : "true",
	dragStart : function(event) {
		var dataTransfer = event.originalEvent.dataTransfer;
		dataTransfer.setData("Text", this.get("elementId"));
	}
});

DragNDrop.Droppable = Ember.Mixin.create({
	dragEnter : DragNDrop.cancel,
	dragOver : DragNDrop.cancel,
	drop : function(event) {
		event.preventDefault();
		return false;
	}
});

App.ProductView = Ember.View.extend(DragNDrop.Draggable, {
	templateName: 'drag',
	tagName : "span",

	// .setDragImage (in #dragStart) requires an HTML element as the first argument
	// so you must tell Ember to create the view and it"s element and then get the 
	// HTML representation of that element.
	dragIconElement : Ember.View.create({
		attributeBindings : [ "src" ],
		tagName : "img",
		src : "http://twitter.com/api/users/profile_image/twitter"
	}).createElement().get("element"),

	dragStart : function(event) {
		this._super(event);
		// Let the controller know this view is dragging
		this.set("content.isDragging", true);

		// Set the drag image and location relative to the mouse/touch event
		var dataTransfer = event.originalEvent.dataTransfer;
		dataTransfer.setDragImage(this.get("dragIconElement"), 24, 24);
	},

	dragEnd : function(event) {
		// Let the controller know this view is done dragging
		this.set("content.isDragging", false);
	},

	doubleClick : function(event) {
		this.set("content.isAdded", !this.get("content.isAdded"));
	}
});

App.ProductDropTarget = Ember.View.extend(DragNDrop.Droppable, {
	tagName : "div",
	classNames : [ "dropTarget" ],
	classNameBindings : [ "cartAction" ],
	helpText : null,

	// This will determine which class (if any) you should add
	// to
	// the view when you are in the process of dragging an item.
	cartAction : function() {
		if (Ember.isEmpty(this.get("dragContext"))) {
			this.set("helpText", "(Drop Zone)");
			return null;
		}

		if (!this.get("dragContext.isAdded")) {
			this.set("helpText", "(Drop to Add)");
			return "cart-add";
		} else if (this.get("dragContext.isAdded")) {
			this.set("helpText", "(Drop to Remove)");
			return "cart-remove";
		} else {
			this.set("helpText", "(Drop Zone)");
			return null;
		}

	}.property("dragContext"),

	drop : function(event) {
		var viewId = event.originalEvent.dataTransfer
				.getData("Text"), view = Ember.View.views[viewId];

		// Set view properties
		// Must be within `Ember.run.next` to always work
		Ember.run.next(this, function() {
			view.set("content.isAdded", !view
					.get("content.isAdded"));
		});

		return this._super(event);
	}
});

App.DragController = Ember.ArrayController.extend({
	currentDragItem : function() {
		return this.findProperty("isDragging", true);
	}.property("@each.isDragging"),

	productsInCart : function() {
		var cartItems = this.filterProperty("isAdded", true);
		console.log(cartItems);
		if (!Ember.isEmpty(cartItems)) {
			// Sort desc by name
			return cartItems.sort(function(a, b) {
				if ((a.get("name").toLowerCase()) < (b.get("name")
						.toLowerCase()))
					return -1;
				else
					return 1;
			});
		}
	}.property("@each.isAdded")
});