
<%@ page import="testgrails.Iteration" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'iteration.label', default: 'Iteration')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-iteration" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-iteration" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list iteration">
			
				<g:if test="${iterationInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="iteration.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${iterationInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${iterationInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="iteration.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${iterationInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${iterationInstance?.orderId}">
				<li class="fieldcontain">
					<span id="orderId-label" class="property-label"><g:message code="iteration.orderId.label" default="Order Id" /></span>
					
						<span class="property-value" aria-labelledby="orderId-label"><g:fieldValue bean="${iterationInstance}" field="orderId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${iterationInstance?.release}">
				<li class="fieldcontain">
					<span id="release-label" class="property-label"><g:message code="iteration.release.label" default="Release" /></span>
					
						<span class="property-value" aria-labelledby="release-label"><g:link controller="release" action="show" id="${iterationInstance?.release?.id}">${iterationInstance?.release?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${iterationInstance?.scenarios}">
				<li class="fieldcontain">
					<span id="scenarios-label" class="property-label"><g:message code="iteration.scenarios.label" default="Scenarios" /></span>
					
						<g:each in="${iterationInstance.scenarios}" var="s">
						<span class="property-value" aria-labelledby="scenarios-label"><g:link controller="scenario" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${iterationInstance?.id}" />
					<g:link class="edit" action="edit" id="${iterationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
