
<%@ page import="testgrails.Release" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'release.label', default: 'Release')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-release" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-release" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list release">
			
				<g:if test="${releaseInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="release.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${releaseInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${releaseInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="release.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${releaseInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${releaseInstance?.iterations}">
				<li class="fieldcontain">
					<span id="iterations-label" class="property-label"><g:message code="release.iterations.label" default="Iterations" /></span>
					
						<g:each in="${releaseInstance.iterations}" var="i">
						<span class="property-value" aria-labelledby="iterations-label"><g:link controller="iteration" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${releaseInstance?.orderId}">
				<li class="fieldcontain">
					<span id="orderId-label" class="property-label"><g:message code="release.orderId.label" default="Order Id" /></span>
					
						<span class="property-value" aria-labelledby="orderId-label"><g:fieldValue bean="${releaseInstance}" field="orderId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${releaseInstance?.roadMap}">
				<li class="fieldcontain">
					<span id="roadMap-label" class="property-label"><g:message code="release.roadMap.label" default="Road Map" /></span>
					
						<span class="property-value" aria-labelledby="roadMap-label"><g:link controller="roadMap" action="show" id="${releaseInstance?.roadMap?.id}">${releaseInstance?.roadMap?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${releaseInstance?.useCases}">
				<li class="fieldcontain">
					<span id="useCases-label" class="property-label"><g:message code="release.useCases.label" default="Use Cases" /></span>
					
						<g:each in="${releaseInstance.useCases}" var="u">
						<span class="property-value" aria-labelledby="useCases-label"><g:link controller="useCase" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${releaseInstance?.id}" />
					<g:link class="edit" action="edit" id="${releaseInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
