
<%@ page import="testgrails.FirstEffortEstimate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-firstEffortEstimate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-firstEffortEstimate" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="effortType" title="${message(code: 'firstEffortEstimate.effortType.label', default: 'Effort Type')}" />
					
						<g:sortableColumn property="maxEffort" title="${message(code: 'firstEffortEstimate.maxEffort.label', default: 'Max Effort')}" />
					
						<g:sortableColumn property="medEffort" title="${message(code: 'firstEffortEstimate.medEffort.label', default: 'Med Effort')}" />
					
						<g:sortableColumn property="minEffort" title="${message(code: 'firstEffortEstimate.minEffort.label', default: 'Min Effort')}" />
					
						<g:sortableColumn property="risk" title="${message(code: 'firstEffortEstimate.risk.label', default: 'Risk')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${firstEffortEstimateInstanceList}" status="i" var="firstEffortEstimateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${firstEffortEstimateInstance.id}">${fieldValue(bean: firstEffortEstimateInstance, field: "effortType")}</g:link></td>
					
						<td>${fieldValue(bean: firstEffortEstimateInstance, field: "maxEffort")}</td>
					
						<td>${fieldValue(bean: firstEffortEstimateInstance, field: "medEffort")}</td>
					
						<td>${fieldValue(bean: firstEffortEstimateInstance, field: "minEffort")}</td>
					
						<td>${fieldValue(bean: firstEffortEstimateInstance, field: "risk")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${firstEffortEstimateInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
