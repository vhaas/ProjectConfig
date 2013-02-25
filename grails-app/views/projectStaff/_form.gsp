<%@ page import="testgrails.ProjectStaff" %>



<div class="fieldcontain ${hasErrors(bean: projectStaffInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="projectStaff.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${projectStaffInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectStaffInstance, field: 'plannedEfforts', 'error')} ">
	<label for="plannedEfforts">
		<g:message code="projectStaff.plannedEfforts.label" default="Planned Efforts" />
		
	</label>
	<g:select name="plannedEfforts" from="${testgrails.PlannedEffort.list()}" multiple="multiple" optionKey="id" size="5" value="${projectStaffInstance?.plannedEfforts*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectStaffInstance, field: 'profiles', 'error')} ">
	<label for="profiles">
		<g:message code="projectStaff.profiles.label" default="Profiles" />
		
	</label>
	
</div>

