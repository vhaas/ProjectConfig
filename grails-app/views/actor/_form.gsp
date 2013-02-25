<%@ page import="testgrails.Actor" %>



<div class="fieldcontain ${hasErrors(bean: actorInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="actor.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${actorInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actorInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="actor.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${actorInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actorInstance, field: 'useCases', 'error')} ">
	<label for="useCases">
		<g:message code="actor.useCases.label" default="Use Cases" />
		
	</label>
	
</div>

