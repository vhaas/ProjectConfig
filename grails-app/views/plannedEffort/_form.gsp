<%@ page import="testgrails.PlannedEffort" %>



<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'effortEstimate', 'error')} required">
	<label for="effortEstimate">
		<g:message code="plannedEffort.effortEstimate.label" default="Effort Estimate" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="effortEstimate" name="effortEstimate.id" from="${testgrails.EffortEstimate.list()}" optionKey="id" required="" value="${plannedEffortInstance?.effortEstimate?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'effortType', 'error')} ">
	<label for="effortType">
		<g:message code="plannedEffort.effortType.label" default="Effort Type" />
		
	</label>
	<g:textField name="effortType" value="${plannedEffortInstance?.effortType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'maxEffort', 'error')} required">
	<label for="maxEffort">
		<g:message code="plannedEffort.maxEffort.label" default="Max Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxEffort" type="number" value="${plannedEffortInstance.maxEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'medEffort', 'error')} required">
	<label for="medEffort">
		<g:message code="plannedEffort.medEffort.label" default="Med Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="medEffort" type="number" value="${plannedEffortInstance.medEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'minEffort', 'error')} required">
	<label for="minEffort">
		<g:message code="plannedEffort.minEffort.label" default="Min Effort" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minEffort" type="number" value="${plannedEffortInstance.minEffort}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'moduleChanges', 'error')} ">
	<label for="moduleChanges">
		<g:message code="plannedEffort.moduleChanges.label" default="Module Changes" />
		
	</label>
	<g:select name="moduleChanges" from="${testgrails.ModuleChange.list()}" multiple="multiple" optionKey="id" size="5" value="${plannedEffortInstance?.moduleChanges*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'neededProfiles', 'error')} ">
	<label for="neededProfiles">
		<g:message code="plannedEffort.neededProfiles.label" default="Needed Profiles" />
		
	</label>
	<g:select name="neededProfiles" from="${testgrails.Profile.list()}" multiple="multiple" optionKey="id" size="5" value="${plannedEffortInstance?.neededProfiles*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'plannedEfforts', 'error')} ">
	<label for="plannedEfforts">
		<g:message code="plannedEffort.plannedEfforts.label" default="Planned Efforts" />
		
	</label>
	<g:select name="plannedEfforts" from="${testgrails.PlannedEffort.list()}" multiple="multiple" optionKey="id" size="5" value="${plannedEffortInstance?.plannedEfforts*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: plannedEffortInstance, field: 'risk', 'error')} required">
	<label for="risk">
		<g:message code="plannedEffort.risk.label" default="Risk" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="risk" type="number" value="${plannedEffortInstance.risk}" required=""/>
</div>

