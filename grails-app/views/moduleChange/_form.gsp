<%@ page import="testgrails.ModuleChange" %>



<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'adaptionAspect', 'error')} ">
	<label for="adaptionAspect">
		<g:message code="moduleChange.adaptionAspect.label" default="Adaption Aspect" />
		
	</label>
	<g:textField name="adaptionAspect" value="${moduleChangeInstance?.adaptionAspect}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'adaptionType', 'error')} ">
	<label for="adaptionType">
		<g:message code="moduleChange.adaptionType.label" default="Adaption Type" />
		
	</label>
	<g:textField name="adaptionType" value="${moduleChangeInstance?.adaptionType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'effortEstimate', 'error')} required">
	<label for="effortEstimate">
		<g:message code="moduleChange.effortEstimate.label" default="Effort Estimate" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="effortEstimate" name="effortEstimate.id" from="${testgrails.EffortEstimate.list()}" optionKey="id" required="" value="${moduleChangeInstance?.effortEstimate?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'interfaceChanges', 'error')} ">
	<label for="interfaceChanges">
		<g:message code="moduleChange.interfaceChanges.label" default="Interface Changes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${moduleChangeInstance?.interfaceChanges?}" var="i">
    <li><g:link controller="interfaceChange" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="interfaceChange" action="create" params="['moduleChange.id': moduleChangeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="moduleChange.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${moduleChangeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'requiredModules', 'error')} ">
	<label for="requiredModules">
		<g:message code="moduleChange.requiredModules.label" default="Required Modules" />
		
	</label>
	<g:select name="requiredModules" from="${testgrails.ModuleChange.list()}" multiple="multiple" optionKey="id" size="5" value="${moduleChangeInstance?.requiredModules*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'systemChange', 'error')} required">
	<label for="systemChange">
		<g:message code="moduleChange.systemChange.label" default="System Change" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="systemChange" name="systemChange.id" from="${testgrails.SystemChange.list()}" optionKey="id" required="" value="${moduleChangeInstance?.systemChange?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleChangeInstance, field: 'useCases', 'error')} ">
	<label for="useCases">
		<g:message code="moduleChange.useCases.label" default="Use Cases" />
		
	</label>
	<g:select name="useCases" from="${testgrails.UseCase.list()}" multiple="multiple" optionKey="id" size="5" value="${moduleChangeInstance?.useCases*.id}" class="many-to-many"/>
</div>

