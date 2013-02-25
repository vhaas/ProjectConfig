
<%@ page import="testgrails.Scenario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scenario.label', default: 'Scenario')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-scenario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-scenario" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list scenario">
			
				<g:if test="${scenarioInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="scenario.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${scenarioInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scenarioInstance?.interfaceChanges}">
				<li class="fieldcontain">
					<span id="interfaceChanges-label" class="property-label"><g:message code="scenario.interfaceChanges.label" default="Interface Changes" /></span>
					
						<g:each in="${scenarioInstance.interfaceChanges}" var="i">
						<span class="property-value" aria-labelledby="interfaceChanges-label"><g:link controller="interfaceChange" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${scenarioInstance?.iteration}">
				<li class="fieldcontain">
					<span id="iteration-label" class="property-label"><g:message code="scenario.iteration.label" default="Iteration" /></span>
					
						<span class="property-value" aria-labelledby="iteration-label"><g:link controller="iteration" action="show" id="${scenarioInstance?.iteration?.id}">${scenarioInstance?.iteration?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scenarioInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="scenario.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${scenarioInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scenarioInstance?.postConditions}">
				<li class="fieldcontain">
					<span id="postConditions-label" class="property-label"><g:message code="scenario.postConditions.label" default="Post Conditions" /></span>
					
						<span class="property-value" aria-labelledby="postConditions-label"><g:fieldValue bean="${scenarioInstance}" field="postConditions"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scenarioInstance?.steps}">
				<li class="fieldcontain">
					<span id="steps-label" class="property-label"><g:message code="scenario.steps.label" default="Steps" /></span>
					
						<g:each in="${scenarioInstance.steps}" var="s">
						<span class="property-value" aria-labelledby="steps-label"><g:link controller="step" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${scenarioInstance?.trigger}">
				<li class="fieldcontain">
					<span id="trigger-label" class="property-label"><g:message code="scenario.trigger.label" default="Trigger" /></span>
					
						<span class="property-value" aria-labelledby="trigger-label"><g:fieldValue bean="${scenarioInstance}" field="trigger"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${scenarioInstance?.useCase}">
				<li class="fieldcontain">
					<span id="useCase-label" class="property-label"><g:message code="scenario.useCase.label" default="Use Case" /></span>
					
						<span class="property-value" aria-labelledby="useCase-label"><g:link controller="useCase" action="show" id="${scenarioInstance?.useCase?.id}">${scenarioInstance?.useCase?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scenarioInstance?.id}" />
					<g:link class="edit" action="edit" id="${scenarioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
