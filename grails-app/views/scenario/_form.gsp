<%@ page import="testgrails.Scenario" %>



<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="scenario.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${scenarioInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'interfaceChanges', 'error')} ">
	<label for="interfaceChanges">
		<g:message code="scenario.interfaceChanges.label" default="Interface Changes" />
		
	</label>
	<g:select name="interfaceChanges" from="${testgrails.InterfaceChange.list()}" multiple="multiple" optionKey="id" size="5" value="${scenarioInstance?.interfaceChanges*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'iteration', 'error')} required">
	<label for="iteration">
		<g:message code="scenario.iteration.label" default="Iteration" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="iteration" name="iteration.id" from="${testgrails.Iteration.list()}" optionKey="id" required="" value="${scenarioInstance?.iteration?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="scenario.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${scenarioInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'postConditions', 'error')} ">
	<label for="postConditions">
		<g:message code="scenario.postConditions.label" default="Post Conditions" />
		
	</label>
	<g:textField name="postConditions" value="${scenarioInstance?.postConditions}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'steps', 'error')} ">
	<label for="steps">
		<g:message code="scenario.steps.label" default="Steps" />
		
	</label>
	<g:select name="steps" from="${testgrails.Step.list()}" multiple="multiple" optionKey="id" size="5" value="${scenarioInstance?.steps*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'trigger', 'error')} ">
	<label for="trigger">
		<g:message code="scenario.trigger.label" default="Trigger" />
		
	</label>
	<g:textField name="trigger" value="${scenarioInstance?.trigger}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scenarioInstance, field: 'useCase', 'error')} required">
	<label for="useCase">
		<g:message code="scenario.useCase.label" default="Use Case" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="useCase" name="useCase.id" from="${testgrails.UseCase.list()}" optionKey="id" required="" value="${scenarioInstance?.useCase?.id}" class="many-to-one"/>
</div>

