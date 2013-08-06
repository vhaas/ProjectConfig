<script type="text/x-handlebars" data-template-name="add-system-change-modal">
	<div class="modal">
  		<div class="modal-header">
    		Add System Change
		</div>
  		<form {{action save on="submit"}}>
    		<div class="modal-body">
      			<div>
        			<button class="btn pull-left"{{action "doda"}}>Create new System Change</button>
      			</div>
      			<div class="pull-right">
					{{view App.SelectFilterSystem
						contentBinding="controller.controllers.systemsTable.content"
						selectionBinding="controller.selectedFilter"
						prompt="Filter Systems"
					}}
      			</div>
      			<table class="table table-bordered table-hover">
					<thead>
		      			<tr>
		        	 		<th>System Change</th>
		         			<th>System</th>
		         			<th>Adaption Type</th>
		         			<th>User Story count</th>
		      			</tr>
		   			</thead>
		   			<tbody>
						{{#each item in controller}}
							<tr {{bindAttr class="isSelected::info"}} {{action "selectSystemChange" item target="controller"}}>
								<td>
									{{item.adaptionAspect}}
								</td>
								<td>
									{{item.system.name}}
								</td>
								<td>
									{{item.adaptionType.name}}
								</td>
								<td>
									{{item.userStories.length}}
								</td>
							</tr>
						{{/each}}
					</tbody>
				</table>
    		</div>
			<div class="modal-footer">
      			<button class="btn" style="width:6em" type="submit" {{bindAttr disabled="shouldDisableSubmit"}}>Save</button>
      			<button class="btn" style="width:6em" {{action close}}>Cancel</button>
    		</div>
  		</form>
	</div>
</script>