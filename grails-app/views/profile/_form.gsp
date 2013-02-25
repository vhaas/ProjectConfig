<%@ page import="testgrails.Profile" %>



<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="profile.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${profileInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'effortEstimations', 'error')} ">
	<label for="effortEstimations">
		<g:message code="profile.effortEstimations.label" default="Effort Estimations" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="profile.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${profileInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'projectStaff', 'error')} ">
	<label for="projectStaff">
		<g:message code="profile.projectStaff.label" default="Project Staff" />
		
	</label>
	<g:select name="projectStaff" from="${testgrails.ProjectStaff.list()}" multiple="multiple" optionKey="id" size="5" value="${profileInstance?.projectStaff*.id}" class="many-to-many"/>
</div>

