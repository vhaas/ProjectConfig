<%@ page import="testgrails.Release" %>



<div class="fieldcontain ${hasErrors(bean: releaseInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="release.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${releaseInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: releaseInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="release.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${releaseInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: releaseInstance, field: 'iterations', 'error')} ">
	<label for="iterations">
		<g:message code="release.iterations.label" default="Iterations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${releaseInstance?.iterations?}" var="i">
    <li><g:link controller="iteration" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="iteration" action="create" params="['release.id': releaseInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'iteration.label', default: 'Iteration')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: releaseInstance, field: 'orderId', 'error')} required">
	<label for="orderId">
		<g:message code="release.orderId.label" default="Order Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="orderId" type="number" value="${releaseInstance.orderId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: releaseInstance, field: 'roadMap', 'error')} required">
	<label for="roadMap">
		<g:message code="release.roadMap.label" default="Road Map" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="roadMap" name="roadMap.id" from="${testgrails.RoadMap.list()}" optionKey="id" required="" value="${releaseInstance?.roadMap?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: releaseInstance, field: 'useCases', 'error')} ">
	<label for="useCases">
		<g:message code="release.useCases.label" default="Use Cases" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${releaseInstance?.useCases?}" var="u">
    <li><g:link controller="useCase" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="useCase" action="create" params="['release.id': releaseInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'useCase.label', default: 'UseCase')])}</g:link>
</li>
</ul>

</div>

