
<%@ page import="testgrails.SystemChange" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'systemChange.label', default: 'SystemChange')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-systemChange" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-systemChange" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list systemChange">
			
				<g:if test="${systemChangeInstance?.adaptionAspect}">
				<li class="fieldcontain">
					<span id="adaptionAspect-label" class="property-label"><g:message code="systemChange.adaptionAspect.label" default="Adaption Aspect" /></span>
					
						<span class="property-value" aria-labelledby="adaptionAspect-label"><g:fieldValue bean="${systemChangeInstance}" field="adaptionAspect"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${systemChangeInstance?.adaptionType}">
				<li class="fieldcontain">
					<span id="adaptionType-label" class="property-label"><g:message code="systemChange.adaptionType.label" default="Adaption Type" /></span>
					
						<span class="property-value" aria-labelledby="adaptionType-label"><g:fieldValue bean="${systemChangeInstance}" field="adaptionType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${systemChangeInstance?.firstEffortEstimate}">
				<li class="fieldcontain">
					<span id="firstEffortEstimate-label" class="property-label"><g:message code="systemChange.firstEffortEstimate.label" default="First Effort Estimate" /></span>
					
						<span class="property-value" aria-labelledby="firstEffortEstimate-label"><g:link controller="firstEffortEstimate" action="show" id="${systemChangeInstance?.firstEffortEstimate?.id}">${systemChangeInstance?.firstEffortEstimate?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${systemChangeInstance?.moduleChanges}">
				<li class="fieldcontain">
					<span id="moduleChanges-label" class="property-label"><g:message code="systemChange.moduleChanges.label" default="Module Changes" /></span>
					
						<g:each in="${systemChangeInstance.moduleChanges}" var="m">
						<span class="property-value" aria-labelledby="moduleChanges-label"><g:link controller="moduleChange" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${systemChangeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="systemChange.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${systemChangeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${systemChangeInstance?.userStories}">
				<li class="fieldcontain">
					<span id="userStories-label" class="property-label"><g:message code="systemChange.userStories.label" default="User Stories" /></span>
					
						<g:each in="${systemChangeInstance.userStories}" var="u">
						<span class="property-value" aria-labelledby="userStories-label"><g:link controller="userStory" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${systemChangeInstance?.id}" />
					<g:link class="edit" action="edit" id="${systemChangeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
