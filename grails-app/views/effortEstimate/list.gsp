
<%@ page import="testgrails.EffortEstimate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'effortEstimate.label', default: 'EffortEstimate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-effortEstimate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-effortEstimate" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="effortType" title="${message(code: 'effortEstimate.effortType.label', default: 'Effort Type')}" />
					
						<th><g:message code="effortEstimate.firstEffortEstimate.label" default="First Effort Estimate" /></th>
					
						<g:sortableColumn property="maxEffort" title="${message(code: 'effortEstimate.maxEffort.label', default: 'Max Effort')}" />
					
						<g:sortableColumn property="medEffort" title="${message(code: 'effortEstimate.medEffort.label', default: 'Med Effort')}" />
					
						<g:sortableColumn property="minEffort" title="${message(code: 'effortEstimate.minEffort.label', default: 'Min Effort')}" />
					
						<g:sortableColumn property="risk" title="${message(code: 'effortEstimate.risk.label', default: 'Risk')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${effortEstimateInstanceList}" status="i" var="effortEstimateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${effortEstimateInstance.id}">${fieldValue(bean: effortEstimateInstance, field: "effortType")}</g:link></td>
					
						<td>${fieldValue(bean: effortEstimateInstance, field: "firstEffortEstimate")}</td>
					
						<td>${fieldValue(bean: effortEstimateInstance, field: "maxEffort")}</td>
					
						<td>${fieldValue(bean: effortEstimateInstance, field: "medEffort")}</td>
					
						<td>${fieldValue(bean: effortEstimateInstance, field: "minEffort")}</td>
					
						<td>${fieldValue(bean: effortEstimateInstance, field: "risk")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${effortEstimateInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
