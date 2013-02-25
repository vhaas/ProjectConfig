<%@ page import="testgrails.SystemChange" %>



<div class="fieldcontain ${hasErrors(bean: systemChangeInstance, field: 'adaptionAspect', 'error')} ">
	<label for="adaptionAspect">
		<g:message code="systemChange.adaptionAspect.label" default="Adaption Aspect" />
		
	</label>
	<g:textField name="adaptionAspect" value="${systemChangeInstance?.adaptionAspect}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: systemChangeInstance, field: 'adaptionType', 'error')} ">
	<label for="adaptionType">
		<g:message code="systemChange.adaptionType.label" default="Adaption Type" />
		
	</label>
	<g:textField name="adaptionType" value="${systemChangeInstance?.adaptionType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: systemChangeInstance, field: 'firstEffortEstimate', 'error')} required">
	<label for="firstEffortEstimate">
		<g:message code="systemChange.firstEffortEstimate.label" default="First Effort Estimate" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="firstEffortEstimate" name="firstEffortEstimate.id" from="${testgrails.FirstEffortEstimate.list()}" optionKey="id" required="" value="${systemChangeInstance?.firstEffortEstimate?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: systemChangeInstance, field: 'moduleChanges', 'error')} ">
	<label for="moduleChanges">
		<g:message code="systemChange.moduleChanges.label" default="Module Changes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${systemChangeInstance?.moduleChanges?}" var="m">
    <li><g:link controller="moduleChange" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="moduleChange" action="create" params="['systemChange.id': systemChangeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'moduleChange.label', default: 'ModuleChange')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: systemChangeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="systemChange.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${systemChangeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: systemChangeInstance, field: 'userStories', 'error')} ">
	<label for="userStories">
		<g:message code="systemChange.userStories.label" default="User Stories" />
		
	</label>
	
</div>

