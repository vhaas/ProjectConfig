
<%@ page import="testgrails.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-profile" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list profile">
			
				<g:if test="${profileInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="profile.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${profileInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.effortEstimations}">
				<li class="fieldcontain">
					<span id="effortEstimations-label" class="property-label"><g:message code="profile.effortEstimations.label" default="Effort Estimations" /></span>
					
						<g:each in="${profileInstance.effortEstimations}" var="e">
						<span class="property-value" aria-labelledby="effortEstimations-label"><g:link controller="effortEstimate" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="profile.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${profileInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.projectStaff}">
				<li class="fieldcontain">
					<span id="projectStaff-label" class="property-label"><g:message code="profile.projectStaff.label" default="Project Staff" /></span>
					
						<g:each in="${profileInstance.projectStaff}" var="p">
						<span class="property-value" aria-labelledby="projectStaff-label"><g:link controller="projectStaff" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${profileInstance?.id}" />
					<g:link class="edit" action="edit" id="${profileInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
