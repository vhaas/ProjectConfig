<%@ page import="testgrails.RoadMap" %>



<div class="fieldcontain ${hasErrors(bean: roadMapInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="roadMap.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${roadMapInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: roadMapInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="roadMap.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${roadMapInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roadMapInstance, field: 'orderId', 'error')} required">
	<label for="orderId">
		<g:message code="roadMap.orderId.label" default="Order Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="orderId" type="number" value="${roadMapInstance.orderId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: roadMapInstance, field: 'releases', 'error')} ">
	<label for="releases">
		<g:message code="roadMap.releases.label" default="Releases" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${roadMapInstance?.releases?}" var="r">
    <li><g:link controller="release" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="release" action="create" params="['roadMap.id': roadMapInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'release.label', default: 'Release')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: roadMapInstance, field: 'userStories', 'error')} ">
	<label for="userStories">
		<g:message code="roadMap.userStories.label" default="User Stories" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${roadMapInstance?.userStories?}" var="u">
    <li><g:link controller="userStory" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="userStory" action="create" params="['roadMap.id': roadMapInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'userStory.label', default: 'UserStory')])}</g:link>
</li>
</ul>

</div>

