
<%@ page import="testgrails.PreConditions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'preConditions.label', default: 'PreConditions')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-preConditions" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-preConditions" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list preConditions">
			
				<g:if test="${preConditionsInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="preConditions.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${preConditionsInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${preConditionsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="preConditions.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${preConditionsInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${preConditionsInstance?.useCases}">
				<li class="fieldcontain">
					<span id="useCases-label" class="property-label"><g:message code="preConditions.useCases.label" default="Use Cases" /></span>
					
						<g:each in="${preConditionsInstance.useCases}" var="u">
						<span class="property-value" aria-labelledby="useCases-label"><g:link controller="useCase" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${preConditionsInstance?.id}" />
					<g:link class="edit" action="edit" id="${preConditionsInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
