<script type="text/x-handlebars" data-template-name="systemchange-list">
	<section style="height: 50em; overflow-x:auto">
		<table class="table">
			<tr class="pull-left">
				{{#each item in controller}}
					<td>
						<section class="form-horizontal" style="height:45em;width:30em">
							<h4>
								{{item.system.name}}
								{{#if isDirty}}
									<i class="icon-exclamation-sign"></i>
								{{/if}}
							</h4>
							<section>
								<div class="control-group">

									{{#view App.BootstrapControl inputId="controller.editMode" label="Edit Mode"}}
										{{view Ember.Checkbox checkedBinding="controller.editMode"}}
									{{/view}}

									{{#view App.BootstrapControl inputId="system" label="System"}}
										{{view App.SystemSelect
											disabledBinding="controller.isDisabled"
											contentBinding="controller.controllers.roles.arrangedContent"
											selectionBinding="system"
											valueBinding="system_id"
											prompt="Select System"}}
											{{#unless hasSystem}}
												<button class="btn" {{action "editRole" role}}{{bindAttr disabled="controller.isDisabled"}}>edit</button>
											{{/unless}}
											<button class="btn btn-secondary" {{action "createRole" this}}{{bindAttr disabled="controller.isDisabled"}}>create</button>
									{{/view}}

									{{#view App.BootstrapControl inputId="adaptionType" label="Adaption Type"}}
										{{view App.AdaptionTypeSelect
											disabledBinding="controller.isDisabled"
											contentBinding="controller.controllers.roles.arrangedContent"
											selectionBinding="system"
											valueBinding="system_id"
											prompt="Select Adaption Type"}}
										<button class="btn btn-secondary" {{action "createRole" this}}{{bindAttr disabled="controller.isDisabled"}}>add Adaption Type</button>
									{{/view}}

									{{#view App.BootstrapControl inputId="adaptionAspect" label="Adaption Aspect"}}
										{{view Ember.TextArea disabledBinding="controller.isDisabled" valueBinding="adaptionAspect"}}
									{{/view}}

									{{#if editMode}}
										<div class="controls">
											<button type="submit" class="btn btn-primary">Save</button>
											<button class="btn btn-secondary" {{action "discardChanges"}}>Discard</button>
										</div>
									{{/if}}
								</div>
							</section>
						</section>
					</td>
				{{/each}}
			</tr>
		</table>
	</section>
</script>