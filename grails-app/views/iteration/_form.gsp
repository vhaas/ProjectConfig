<%@ page import="testgrails.Iteration" %>



<div class="fieldcontain ${hasErrors(bean: iterationInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="iteration.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${iterationInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: iterationInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="iteration.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${iterationInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: iterationInstance, field: 'orderId', 'error')} required">
	<label for="orderId">
		<g:message code="iteration.orderId.label" default="Order Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="orderId" type="number" value="${iterationInstance.orderId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: iterationInstance, field: 'release', 'error')} required">
	<label for="release">
		<g:message code="iteration.release.label" default="Release" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="release" name="release.id" from="${testgrails.Release.list()}" optionKey="id" required="" value="${iterationInstance?.release?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: iterationInstance, field: 'scenarios', 'error')} ">
	<label for="scenarios">
		<g:message code="iteration.scenarios.label" default="Scenarios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${iterationInstance?.scenarios?}" var="s">
    <li><g:link controller="scenario" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="scenario" action="create" params="['iteration.id': iterationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'scenario.label', default: 'Scenario')])}</g:link>
</li>
</ul>

</div>

