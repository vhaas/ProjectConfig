
<%@ page import="testgrails.ProjectStaff" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'projectStaff.label', default: 'ProjectStaff')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-projectStaff" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-projectStaff" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list projectStaff">
			
				<g:if test="${projectStaffInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="projectStaff.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${projectStaffInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectStaffInstance?.plannedEfforts}">
				<li class="fieldcontain">
					<span id="plannedEfforts-label" class="property-label"><g:message code="projectStaff.plannedEfforts.label" default="Planned Efforts" /></span>
					
						<g:each in="${projectStaffInstance.plannedEfforts}" var="p">
						<span class="property-value" aria-labelledby="plannedEfforts-label"><g:link controller="plannedEffort" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${projectStaffInstance?.profiles}">
				<li class="fieldcontain">
					<span id="profiles-label" class="property-label"><g:message code="projectStaff.profiles.label" default="Profiles" /></span>
					
						<g:each in="${projectStaffInstance.profiles}" var="p">
						<span class="property-value" aria-labelledby="profiles-label"><g:link controller="profile" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${projectStaffInstance?.id}" />
					<g:link class="edit" action="edit" id="${projectStaffInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
