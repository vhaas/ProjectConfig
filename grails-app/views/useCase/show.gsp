
<%@ page import="testgrails.UseCase" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'useCase.label', default: 'UseCase')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-useCase" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-useCase" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list useCase">
			
				<g:if test="${useCaseInstance?.actors}">
				<li class="fieldcontain">
					<span id="actors-label" class="property-label"><g:message code="useCase.actors.label" default="Actors" /></span>
					
						<g:each in="${useCaseInstance.actors}" var="a">
						<span class="property-value" aria-labelledby="actors-label"><g:link controller="actor" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="useCase.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${useCaseInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.extendUseCases}">
				<li class="fieldcontain">
					<span id="extendUseCases-label" class="property-label"><g:message code="useCase.extendUseCases.label" default="Extend Use Cases" /></span>
					
						<g:each in="${useCaseInstance.extendUseCases}" var="e">
						<span class="property-value" aria-labelledby="extendUseCases-label"><g:link controller="useCase" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.goal}">
				<li class="fieldcontain">
					<span id="goal-label" class="property-label"><g:message code="useCase.goal.label" default="Goal" /></span>
					
						<span class="property-value" aria-labelledby="goal-label"><g:fieldValue bean="${useCaseInstance}" field="goal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.includedUseCases}">
				<li class="fieldcontain">
					<span id="includedUseCases-label" class="property-label"><g:message code="useCase.includedUseCases.label" default="Included Use Cases" /></span>
					
						<g:each in="${useCaseInstance.includedUseCases}" var="i">
						<span class="property-value" aria-labelledby="includedUseCases-label"><g:link controller="useCase" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="useCase.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${useCaseInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.preConditions}">
				<li class="fieldcontain">
					<span id="preConditions-label" class="property-label"><g:message code="useCase.preConditions.label" default="Pre Conditions" /></span>
					
						<g:each in="${useCaseInstance.preConditions}" var="p">
						<span class="property-value" aria-labelledby="preConditions-label"><g:link controller="preConditions" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.release}">
				<li class="fieldcontain">
					<span id="release-label" class="property-label"><g:message code="useCase.release.label" default="Release" /></span>
					
						<span class="property-value" aria-labelledby="release-label"><g:link controller="release" action="show" id="${useCaseInstance?.release?.id}">${useCaseInstance?.release?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.requiredUseCases}">
				<li class="fieldcontain">
					<span id="requiredUseCases-label" class="property-label"><g:message code="useCase.requiredUseCases.label" default="Required Use Cases" /></span>
					
						<g:each in="${useCaseInstance.requiredUseCases}" var="r">
						<span class="property-value" aria-labelledby="requiredUseCases-label"><g:link controller="useCase" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.scenarios}">
				<li class="fieldcontain">
					<span id="scenarios-label" class="property-label"><g:message code="useCase.scenarios.label" default="Scenarios" /></span>
					
						<g:each in="${useCaseInstance.scenarios}" var="s">
						<span class="property-value" aria-labelledby="scenarios-label"><g:link controller="scenario" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${useCaseInstance?.userStories}">
				<li class="fieldcontain">
					<span id="userStories-label" class="property-label"><g:message code="useCase.userStories.label" default="User Stories" /></span>
					
						<g:each in="${useCaseInstance.userStories}" var="u">
						<span class="property-value" aria-labelledby="userStories-label"><g:link controller="userStory" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${useCaseInstance?.id}" />
					<g:link class="edit" action="edit" id="${useCaseInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
