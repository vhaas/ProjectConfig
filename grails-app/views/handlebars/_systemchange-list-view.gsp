<script type="text/x-handlebars" data-template-name="systemchange-list">
	<section style="height: 50em; overflow-x:auto">
		<table class="table2">
			<tr class="pull-left">
				{{#each item in controller}}
					<td>
						<section style="height:45em;width:30em">
							<form {{action save on="submit"}}>
								<blockquote>
									<h5>
										System Change
									</h5>
								</blockquote>
								<div class="control-group form-horizontal">

									{{#view App.BootstrapControl inputId="controller.editMode" label="Edit Mode"}}
										{{view Ember.Checkbox checkedBinding="controller.editMode"}}
									{{/view}}

									{{#unless isDisabled}}

										{{#view App.BootstrapControl inputId="system" label="System"}}
											{{view App.SystemSelect
												disabledBinding="controller.isDisabled"
												contentBinding="App.SystemsTableController.content"
												selectionBinding="system"
												valueBinding="system_id"
												prompt="Select System"}}
											{{#if editMode}}
												{{#unless hasSystem}}
													<button class="btn" {{action "editRole" role}}{{bindAttr disabled="controller.isDisabled"}}>edit</button>
												{{/unless}}
												<button class="btn btn-secondary" {{action "createRole" this}}{{bindAttr disabled="controller.isDisabled"}}>create</button>
											{{/if}}
										{{/view}}

										{{#view App.BootstrapControl inputId="adaptionType" label="Adaption Type"}}
											{{view App.AdaptionTypeSelect
												disabledBinding="controller.isDisabled"
												contentBinding="controller.controllers.roles.adaptionTypeList.content"
												selectionBinding="adaptionType"
												valueBinding="adaption_type_id"
												prompt="Select Adaption Type"}}
											{{#if editMode}}
												<button class="btn btn-secondary" {{action "createRole" this}}{{bindAttr disabled="controller.isDisabled"}}>add Adaption Type</button>
											{{/if}}
										{{/view}}

										{{#view App.BootstrapControl inputId="adaptionAspect" label="Adaption Aspect"}}
											{{view Ember.TextArea disabledBinding="controller.isDisabled" valueBinding="adaptionAspect"}}
										{{/view}}


											<div class="controls">
												<button type="submit" class="btn btn-primary">Save</button>
												<button class="btn btn-secondary" {{action "discardChanges"}}>Discard</button>
											</div>
									{{else}}
										<table class"table table-striped" style="margin-left:40px">
											<tr>
												<td><p>System:</p></td>
												<td><p>{{item.system.name}}</p></td>
											</tr>
											<tr>
												<td><p>Adaption Type:</p></td>
												<td><p>{{item.adaptionType.name}}</p></td>
											</tr>
											<tr>
												<td><p>Adaption Aspect:</p></td>
												<td><p>{{item.adaptionAspect}}</p></td>
											</tr>
										</table>
									{{/unless}}
								</div>
							</form>

						<hr>

							<blockquote>
								<h5>
									Effort
								</h5>
							</blockquote>
							{{#each effort in item.firstEffortEstimates}}
								<div class="control-group">
									<table class="table2 table-condensed">
										<td>
											{{effort.effortRole.name}}
										</td>
										<td class="center">
											{{#view App.BootstrapControl inputId="effort.minEffort" label="min"}}
												{{view Ember.TextField disabledBinding="controller.isDisabled" valueBinding="effort.minEffort" class="input-mini text-right"}}
											{{/view}}
										</td>
										<td class="center">
											{{#view App.BootstrapControl inputId="effort.medEffort" label="med"}}
												{{view Ember.TextField disabledBinding="controller.isDisabled" valueBinding="effort.medEffort" class="input-mini text-right"}}
											{{/view}}
										</td>
										<td class="center">
											{{#view App.BootstrapControl inputId="effort.maxEffort" label="max"}}
												{{view Ember.TextField disabledBinding="controller.isDisabled" valueBinding="effort.maxEffort" class="input-mini text-right"}}
											{{/view}}
										</td>
									</table>
								</div>
							{{/each}}

						<hr>

							<table class="table2" style="height:10em;overflow-y:auto"">
								{{#each story in item.userStories}}
									<tr class="border">
										<td>
											{{story.name}}
										</td>
										<td>

										</td>
										<td>
											<button type="button" class="close" {{action "removeUserstory" story}}>&times;</button>
										</td>
									</tr>
								{{/each}}
							</table>
						</section>
					</td>
				{{/each}}
			</tr>
		</table>
	</section>
</script>