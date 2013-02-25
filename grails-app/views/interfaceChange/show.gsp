
<%@ page import="testgrails.InterfaceChange" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'interfaceChange.label', default: 'InterfaceChange')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-interfaceChange" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-interfaceChange" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list interfaceChange">
			
				<g:if test="${interfaceChangeInstance?.adaptionAspect}">
				<li class="fieldcontain">
					<span id="adaptionAspect-label" class="property-label"><g:message code="interfaceChange.adaptionAspect.label" default="Adaption Aspect" /></span>
					
						<span class="property-value" aria-labelledby="adaptionAspect-label"><g:fieldValue bean="${interfaceChangeInstance}" field="adaptionAspect"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${interfaceChangeInstance?.adaptionType}">
				<li class="fieldcontain">
					<span id="adaptionType-label" class="property-label"><g:message code="interfaceChange.adaptionType.label" default="Adaption Type" /></span>
					
						<span class="property-value" aria-labelledby="adaptionType-label"><g:fieldValue bean="${interfaceChangeInstance}" field="adaptionType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${interfaceChangeInstance?.moduleChange}">
				<li class="fieldcontain">
					<span id="moduleChange-label" class="property-label"><g:message code="interfaceChange.moduleChange.label" default="Module Change" /></span>
					
						<span class="property-value" aria-labelledby="moduleChange-label"><g:link controller="moduleChange" action="show" id="${interfaceChangeInstance?.moduleChange?.id}">${interfaceChangeInstance?.moduleChange?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${interfaceChangeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="interfaceChange.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${interfaceChangeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${interfaceChangeInstance?.plannedEffort}">
				<li class="fieldcontain">
					<span id="plannedEffort-label" class="property-label"><g:message code="interfaceChange.plannedEffort.label" default="Planned Effort" /></span>
					
						<span class="property-value" aria-labelledby="plannedEffort-label"><g:link controller="plannedEffort" action="show" id="${interfaceChangeInstance?.plannedEffort?.id}">${interfaceChangeInstance?.plannedEffort?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${interfaceChangeInstance?.scenarios}">
				<li class="fieldcontain">
					<span id="scenarios-label" class="property-label"><g:message code="interfaceChange.scenarios.label" default="Scenarios" /></span>
					
						<g:each in="${interfaceChangeInstance.scenarios}" var="s">
						<span class="property-value" aria-labelledby="scenarios-label"><g:link controller="scenario" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${interfaceChangeInstance?.id}" />
					<g:link class="edit" action="edit" id="${interfaceChangeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
