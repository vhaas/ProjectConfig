<%@ page import="testgrails.EffortEstimate" %>



<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'effortType', 'error')} ">
	<label for="effortType">
		<g:message code="effortEstimate.effortType.label" default="Effort Type" />
		
	</label>
	<g:textField name="effortType" value="${effortEstimateInstance?.effortType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'firstEffortEstimate', 'error')} required">
	<label for="firstEffortEstimate">
		<g:message code="effortEstimate.firstEffortEstimate.label" default="First Effort Estimate" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="firstEffortEstimate" name="firstEffortEstimate.id" from="${testgrails.FirstEffortEstimate.list()}" optionKey="id" required="" value="${effortEstimateInstance?.firstEffortEstimate?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'maxEffort', 'error')} required">
	<label for="maxEffort">
		<g:message code="effortEstimate.maxEffort.label" default="Max Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxEffort" type="number" value="${effortEstimateInstance.maxEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'medEffort', 'error')} required">
	<label for="medEffort">
		<g:message code="effortEstimate.medEffort.label" default="Med Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="medEffort" type="number" value="${effortEstimateInstance.medEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'minEffort', 'error')} required">
	<label for="minEffort">
		<g:message code="effortEstimate.minEffort.label" default="Min Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minEffort" type="number" value="${effortEstimateInstance.minEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'moduleChanges', 'error')} ">
	<label for="moduleChanges">
		<g:message code="effortEstimate.moduleChanges.label" default="Module Changes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${effortEstimateInstance?.moduleChanges?}" var="m">
    <li><g:link controller="moduleChange" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="moduleChange" action="create" params="['effortEstimate.id': effortEstimateInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'moduleChange.label', default: 'ModuleChange')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'neededProfiles', 'error')} ">
	<label for="neededProfiles">
		<g:message code="effortEstimate.neededProfiles.label" default="Needed Profiles" />
		
	</label>
	<g:select name="neededProfiles" from="${testgrails.Profile.list()}" multiple="multiple" optionKey="id" size="5" value="${effortEstimateInstance?.neededProfiles*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'plannedEfforts', 'error')} ">
	<label for="plannedEfforts">
		<g:message code="effortEstimate.plannedEfforts.label" default="Planned Efforts" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${effortEstimateInstance?.plannedEfforts?}" var="p">
    <li><g:link controller="plannedEffort" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="plannedEffort" action="create" params="['effortEstimate.id': effortEstimateInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: effortEstimateInstance, field: 'risk', 'error')} required">
	<label for="risk">
		<g:message code="effortEstimate.risk.label" default="Risk" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="risk" type="number" value="${effortEstimateInstance.risk}" required=""/>
</div>

