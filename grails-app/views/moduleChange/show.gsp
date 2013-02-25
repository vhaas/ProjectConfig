
<%@ page import="testgrails.ModuleChange" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'moduleChange.label', default: 'ModuleChange')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-moduleChange" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-moduleChange" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list moduleChange">
			
				<g:if test="${moduleChangeInstance?.adaptionAspect}">
				<li class="fieldcontain">
					<span id="adaptionAspect-label" class="property-label"><g:message code="moduleChange.adaptionAspect.label" default="Adaption Aspect" /></span>
					
						<span class="property-value" aria-labelledby="adaptionAspect-label"><g:fieldValue bean="${moduleChangeInstance}" field="adaptionAspect"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleChangeInstance?.adaptionType}">
				<li class="fieldcontain">
					<span id="adaptionType-label" class="property-label"><g:message code="moduleChange.adaptionType.label" default="Adaption Type" /></span>
					
						<span class="property-value" aria-labelledby="adaptionType-label"><g:fieldValue bean="${moduleChangeInstance}" field="adaptionType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleChangeInstance?.effortEstimate}">
				<li class="fieldcontain">
					<span id="effortEstimate-label" class="property-label"><g:message code="moduleChange.effortEstimate.label" default="Effort Estimate" /></span>
					
						<span class="property-value" aria-labelledby="effortEstimate-label"><g:link controller="effortEstimate" action="show" id="${moduleChangeInstance?.effortEstimate?.id}">${moduleChangeInstance?.effortEstimate?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleChangeInstance?.interfaceChanges}">
				<li class="fieldcontain">
					<span id="interfaceChanges-label" class="property-label"><g:message code="moduleChange.interfaceChanges.label" default="Interface Changes" /></span>
					
						<g:each in="${moduleChangeInstance.interfaceChanges}" var="i">
						<span class="property-value" aria-labelledby="interfaceChanges-label"><g:link controller="interfaceChange" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${moduleChangeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="moduleChange.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${moduleChangeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleChangeInstance?.requiredModules}">
				<li class="fieldcontain">
					<span id="requiredModules-label" class="property-label"><g:message code="moduleChange.requiredModules.label" default="Required Modules" /></span>
					
						<g:each in="${moduleChangeInstance.requiredModules}" var="r">
						<span class="property-value" aria-labelledby="requiredModules-label"><g:link controller="moduleChange" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${moduleChangeInstance?.systemChange}">
				<li class="fieldcontain">
					<span id="systemChange-label" class="property-label"><g:message code="moduleChange.systemChange.label" default="System Change" /></span>
					
						<span class="property-value" aria-labelledby="systemChange-label"><g:link controller="systemChange" action="show" id="${moduleChangeInstance?.systemChange?.id}">${moduleChangeInstance?.systemChange?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleChangeInstance?.useCases}">
				<li class="fieldcontain">
					<span id="useCases-label" class="property-label"><g:message code="moduleChange.useCases.label" default="Use Cases" /></span>
					
						<g:each in="${moduleChangeInstance.useCases}" var="u">
						<span class="property-value" aria-labelledby="useCases-label"><g:link controller="useCase" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${moduleChangeInstance?.id}" />
					<g:link class="edit" action="edit" id="${moduleChangeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
