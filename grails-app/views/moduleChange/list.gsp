
<%@ page import="testgrails.ModuleChange" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'moduleChange.label', default: 'ModuleChange')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-moduleChange" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-moduleChange" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="adaptionAspect" title="${message(code: 'moduleChange.adaptionAspect.label', default: 'Adaption Aspect')}" />
					
						<g:sortableColumn property="adaptionType" title="${message(code: 'moduleChange.adaptionType.label', default: 'Adaption Type')}" />
					
						<th><g:message code="moduleChange.effortEstimate.label" default="Effort Estimate" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'moduleChange.name.label', default: 'Name')}" />
					
						<th><g:message code="moduleChange.systemChange.label" default="System Change" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${moduleChangeInstanceList}" status="i" var="moduleChangeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${moduleChangeInstance.id}">${fieldValue(bean: moduleChangeInstance, field: "adaptionAspect")}</g:link></td>
					
						<td>${fieldValue(bean: moduleChangeInstance, field: "adaptionType")}</td>
					
						<td>${fieldValue(bean: moduleChangeInstance, field: "effortEstimate")}</td>
					
						<td>${fieldValue(bean: moduleChangeInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: moduleChangeInstance, field: "systemChange")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${moduleChangeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
