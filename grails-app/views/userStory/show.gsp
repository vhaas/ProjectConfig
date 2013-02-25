
<%@ page import="testgrails.UserStory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userStory.label', default: 'UserStory')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-userStory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-userStory" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list userStory">
			
				<g:if test="${userStoryInstance?.benefit}">
				<li class="fieldcontain">
					<span id="benefit-label" class="property-label"><g:message code="userStory.benefit.label" default="Benefit" /></span>
					
						<span class="property-value" aria-labelledby="benefit-label"><g:fieldValue bean="${userStoryInstance}" field="benefit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userStoryInstance?.epics}">
				<li class="fieldcontain">
					<span id="epics-label" class="property-label"><g:message code="userStory.epics.label" default="Epics" /></span>
					
						<g:each in="${userStoryInstance.epics}" var="e">
						<span class="property-value" aria-labelledby="epics-label"><g:link controller="epic" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userStoryInstance?.goal}">
				<li class="fieldcontain">
					<span id="goal-label" class="property-label"><g:message code="userStory.goal.label" default="Goal" /></span>
					
						<span class="property-value" aria-labelledby="goal-label"><g:fieldValue bean="${userStoryInstance}" field="goal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userStoryInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="userStory.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${userStoryInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userStoryInstance?.roadMap}">
				<li class="fieldcontain">
					<span id="roadMap-label" class="property-label"><g:message code="userStory.roadMap.label" default="Road Map" /></span>
					
						<span class="property-value" aria-labelledby="roadMap-label"><g:link controller="roadMap" action="show" id="${userStoryInstance?.roadMap?.id}">${userStoryInstance?.roadMap?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userStoryInstance?.role}">
				<li class="fieldcontain">
					<span id="role-label" class="property-label"><g:message code="userStory.role.label" default="Role" /></span>
					
						<span class="property-value" aria-labelledby="role-label"><g:link controller="role" action="show" id="${userStoryInstance?.role?.id}">${userStoryInstance?.role?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${userStoryInstance?.systemChanges}">
				<li class="fieldcontain">
					<span id="systemChanges-label" class="property-label"><g:message code="userStory.systemChanges.label" default="System Changes" /></span>
					
						<g:each in="${userStoryInstance.systemChanges}" var="s">
						<span class="property-value" aria-labelledby="systemChanges-label"><g:link controller="systemChange" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userStoryInstance?.useCases}">
				<li class="fieldcontain">
					<span id="useCases-label" class="property-label"><g:message code="userStory.useCases.label" default="Use Cases" /></span>
					
						<g:each in="${userStoryInstance.useCases}" var="u">
						<span class="property-value" aria-labelledby="useCases-label"><g:link controller="useCase" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userStoryInstance?.id}" />
					<g:link class="edit" action="edit" id="${userStoryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
