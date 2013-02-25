<%@ page import="testgrails.Role" %>



<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="role.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${roleInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="role.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${roleInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'userStories', 'error')} ">
	<label for="userStories">
		<g:message code="role.userStories.label" default="User Stories" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${roleInstance?.userStories?}" var="u">
    <li><g:link controller="userStory" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="userStory" action="create" params="['role.id': roleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'userStory.label', default: 'UserStory')])}</g:link>
</li>
</ul>

</div>

