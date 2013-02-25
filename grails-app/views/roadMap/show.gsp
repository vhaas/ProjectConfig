
<%@ page import="testgrails.RoadMap" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'roadMap.label', default: 'RoadMap')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-roadMap" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-roadMap" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list roadMap">
			
				<g:if test="${roadMapInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="roadMap.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${roadMapInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${roadMapInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="roadMap.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${roadMapInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${roadMapInstance?.orderId}">
				<li class="fieldcontain">
					<span id="orderId-label" class="property-label"><g:message code="roadMap.orderId.label" default="Order Id" /></span>
					
						<span class="property-value" aria-labelledby="orderId-label"><g:fieldValue bean="${roadMapInstance}" field="orderId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${roadMapInstance?.releases}">
				<li class="fieldcontain">
					<span id="releases-label" class="property-label"><g:message code="roadMap.releases.label" default="Releases" /></span>
					
						<g:each in="${roadMapInstance.releases}" var="r">
						<span class="property-value" aria-labelledby="releases-label"><g:link controller="release" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${roadMapInstance?.userStories}">
				<li class="fieldcontain">
					<span id="userStories-label" class="property-label"><g:message code="roadMap.userStories.label" default="User Stories" /></span>
					
						<g:each in="${roadMapInstance.userStories}" var="u">
						<span class="property-value" aria-labelledby="userStories-label"><g:link controller="userStory" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${roadMapInstance?.id}" />
					<g:link class="edit" action="edit" id="${roadMapInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
