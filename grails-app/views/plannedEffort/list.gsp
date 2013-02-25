
<%@ page import="testgrails.PlannedEffort" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'plannedEffort.label', default: 'PlannedEffort')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-plannedEffort" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-plannedEffort" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="plannedEffort.effortEstimate.label" default="Effort Estimate" /></th>
					
						<g:sortableColumn property="effortType" title="${message(code: 'plannedEffort.effortType.label', default: 'Effort Type')}" />
					
						<g:sortableColumn property="maxEffort" title="${message(code: 'plannedEffort.maxEffort.label', default: 'Max Effort')}" />
					
						<g:sortableColumn property="medEffort" title="${message(code: 'plannedEffort.medEffort.label', default: 'Med Effort')}" />
					
						<g:sortableColumn property="minEffort" title="${message(code: 'plannedEffort.minEffort.label', default: 'Min Effort')}" />
					
						<g:sortableColumn property="risk" title="${message(code: 'plannedEffort.risk.label', default: 'Risk')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${plannedEffortInstanceList}" status="i" var="plannedEffortInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${plannedEffortInstance.id}">${fieldValue(bean: plannedEffortInstance, field: "effortEstimate")}</g:link></td>
					
						<td>${fieldValue(bean: plannedEffortInstance, field: "effortType")}</td>
					
						<td>${fieldValue(bean: plannedEffortInstance, field: "maxEffort")}</td>
					
						<td>${fieldValue(bean: plannedEffortInstance, field: "medEffort")}</td>
					
						<td>${fieldValue(bean: plannedEffortInstance, field: "minEffort")}</td>
					
						<td>${fieldValue(bean: plannedEffortInstance, field: "risk")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${plannedEffortInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
