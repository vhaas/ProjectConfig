
<%@ page import="testgrails.InterfaceChange" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'interfaceChange.label', default: 'InterfaceChange')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-interfaceChange" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-interfaceChange" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="adaptionAspect" title="${message(code: 'interfaceChange.adaptionAspect.label', default: 'Adaption Aspect')}" />
					
						<g:sortableColumn property="adaptionType" title="${message(code: 'interfaceChange.adaptionType.label', default: 'Adaption Type')}" />
					
						<th><g:message code="interfaceChange.moduleChange.label" default="Module Change" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'interfaceChange.name.label', default: 'Name')}" />
					
						<th><g:message code="interfaceChange.plannedEffort.label" default="Planned Effort" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${interfaceChangeInstanceList}" status="i" var="interfaceChangeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${interfaceChangeInstance.id}">${fieldValue(bean: interfaceChangeInstance, field: "adaptionAspect")}</g:link></td>
					
						<td>${fieldValue(bean: interfaceChangeInstance, field: "adaptionType")}</td>
					
						<td>${fieldValue(bean: interfaceChangeInstance, field: "moduleChange")}</td>
					
						<td>${fieldValue(bean: interfaceChangeInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: interfaceChangeInstance, field: "plannedEffort")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${interfaceChangeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
