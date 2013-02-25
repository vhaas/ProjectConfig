<%@ page import="testgrails.FirstEffortEstimate" %>



<div class="fieldcontain ${hasErrors(bean: firstEffortEstimateInstance, field: 'effortEstimates', 'error')} ">
	<label for="effortEstimates">
		<g:message code="firstEffortEstimate.effortEstimates.label" default="Effort Estimates" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${firstEffortEstimateInstance?.effortEstimates?}" var="e">
    <li><g:link controller="effortEstimate" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="effortEstimate" action="create" params="['firstEffortEstimate.id': firstEffortEstimateInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: firstEffortEstimateInstance, field: 'effortType', 'error')} ">
	<label for="effortType">
		<g:message code="firstEffortEstimate.effortType.label" default="Effort Type" />
		
	</label>
	<g:textField name="effortType" value="${firstEffortEstimateInstance?.effortType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: firstEffortEstimateInstance, field: 'maxEffort', 'error')} required">
	<label for="maxEffort">
		<g:message code="firstEffortEstimate.maxEffort.label" default="Max Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxEffort" type="number" value="${firstEffortEstimateInstance.maxEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: firstEffortEstimateInstance, field: 'medEffort', 'error')} required">
	<label for="medEffort">
		<g:message code="firstEffortEstimate.medEffort.label" default="Med Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="medEffort" type="number" value="${firstEffortEstimateInstance.medEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: firstEffortEstimateInstance, field: 'minEffort', 'error')} required">
	<label for="minEffort">
		<g:message code="firstEffortEstimate.minEffort.label" default="Min Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minEffort" type="number" value="${firstEffortEstimateInstance.minEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: firstEffortEstimateInstance, field: 'risk', 'error')} required">
	<label for="risk">
		<g:message code="firstEffortEstimate.risk.label" default="Risk" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="risk" type="number" value="${firstEffortEstimateInstance.risk}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: firstEffortEstimateInstance, field: 'systemChanges', 'error')} ">
	<label for="systemChanges">
		<g:message code="firstEffortEstimate.systemChanges.label" default="System Changes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${firstEffortEstimateInstance?.systemChanges?}" var="s">
    <li><g:link controller="systemChange" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="systemChange" action="create" params="['firstEffortEstimate.id': firstEffortEstimateInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'systemChange.label', default: 'SystemChange')])}</g:link>
</li>
</ul>

</div>

