<%@ page import="testgrails.Epic" %>



<div class="fieldcontain ${hasErrors(bean: epicInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="epic.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${epicInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: epicInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="epic.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${epicInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: epicInstance, field: 'userStories', 'error')} ">
	<label for="userStories">
		<g:message code="epic.userStories.label" default="User Stories" />
		
	</label>
	<g:select name="userStories" from="${testgrails.UserStory.list()}" multiple="multiple" optionKey="id" size="5" value="${epicInstance?.userStories*.id}" class="many-to-many"/>
</div>

