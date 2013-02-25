<%@ page import="testgrails.InterfaceChange" %>



<div class="fieldcontain ${hasErrors(bean: interfaceChangeInstance, field: 'adaptionAspect', 'error')} ">
	<label for="adaptionAspect">
		<g:message code="interfaceChange.adaptionAspect.label" default="Adaption Aspect" />
		
	</label>
	<g:textField name="adaptionAspect" value="${interfaceChangeInstance?.adaptionAspect}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: interfaceChangeInstance, field: 'adaptionType', 'error')} ">
	<label for="adaptionType">
		<g:message code="interfaceChange.adaptionType.label" default="Adaption Type" />
		
	</label>
	<g:textField name="adaptionType" value="${interfaceChangeInstance?.adaptionType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: interfaceChangeInstance, field: 'moduleChange', 'error')} required">
	<label for="moduleChange">
		<g:message code="interfaceChange.moduleChange.label" default="Module Change" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="moduleChange" name="moduleChange.id" from="${testgrails.ModuleChange.list()}" optionKey="id" required="" value="${interfaceChangeInstance?.moduleChange?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: interfaceChangeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="interfaceChange.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${interfaceChangeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: interfaceChangeInstance, field: 'plannedEffort', 'error')} required">
	<label for="plannedEffort">
		<g:message code="interfaceChange.plannedEffort.label" default="Planned Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="plannedEffort" name="plannedEffort.id" from="${testgrails.PlannedEffort.list()}" optionKey="id" required="" value="${interfaceChangeInstance?.plannedEffort?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: interfaceChangeInstance, field: 'scenarios', 'error')} ">
	<label for="scenarios">
		<g:message code="interfaceChange.scenarios.label" default="Scenarios" />
		
	</label>
	
</div>

