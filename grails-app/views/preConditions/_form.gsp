<%@ page import="testgrails.PreConditions" %>



<div class="fieldcontain ${hasErrors(bean: preConditionsInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="preConditions.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${preConditionsInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: preConditionsInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="preConditions.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${preConditionsInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: preConditionsInstance, field: 'useCases', 'error')} ">
	<label for="useCases">
		<g:message code="preConditions.useCases.label" default="Use Cases" />
		
	</label>
	
</div>

