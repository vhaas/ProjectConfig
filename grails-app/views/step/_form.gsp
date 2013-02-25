<%@ page import="testgrails.Step" %>



<div class="fieldcontain ${hasErrors(bean: stepInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="step.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${stepInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stepInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="step.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${stepInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stepInstance, field: 'orderId', 'error')} required">
	<label for="orderId">
		<g:message code="step.orderId.label" default="Order Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="orderId" type="number" value="${stepInstance.orderId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: stepInstance, field: 'scenarios', 'error')} ">
	<label for="scenarios">
		<g:message code="step.scenarios.label" default="Scenarios" />
		
	</label>
	
</div>

