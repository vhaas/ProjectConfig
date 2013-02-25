
<%@ page import="testgrails.SystemChange" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'systemChange.label', default: 'SystemChange')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-systemChange" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-systemChange" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="adaptionAspect" title="${message(code: 'systemChange.adaptionAspect.label', default: 'Adaption Aspect')}" />
					
						<g:sortableColumn property="adaptionType" title="${message(code: 'systemChange.adaptionType.label', default: 'Adaption Type')}" />
					
						<th><g:message code="systemChange.firstEffortEstimate.label" default="First Effort Estimate" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'systemChange.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${systemChangeInstanceList}" status="i" var="systemChangeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${systemChangeInstance.id}">${fieldValue(bean: systemChangeInstance, field: "adaptionAspect")}</g:link></td>
					
						<td>${fieldValue(bean: systemChangeInstance, field: "adaptionType")}</td>
					
						<td>${fieldValue(bean: systemChangeInstance, field: "firstEffortEstimate")}</td>
					
						<td>${fieldValue(bean: systemChangeInstance, field: "name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${systemChangeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
