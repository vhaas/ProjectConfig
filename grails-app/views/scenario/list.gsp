
<%@ page import="testgrails.Scenario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'scenario.label', default: 'Scenario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-scenario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-scenario" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'scenario.description.label', default: 'Description')}" />
					
						<th><g:message code="scenario.iteration.label" default="Iteration" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'scenario.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="postConditions" title="${message(code: 'scenario.postConditions.label', default: 'Post Conditions')}" />
					
						<g:sortableColumn property="trigger" title="${message(code: 'scenario.trigger.label', default: 'Trigger')}" />
					
						<th><g:message code="scenario.useCase.label" default="Use Case" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scenarioInstanceList}" status="i" var="scenarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scenarioInstance.id}">${fieldValue(bean: scenarioInstance, field: "description")}</g:link></td>
					
						<td>${fieldValue(bean: scenarioInstance, field: "iteration")}</td>
					
						<td>${fieldValue(bean: scenarioInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: scenarioInstance, field: "postConditions")}</td>
					
						<td>${fieldValue(bean: scenarioInstance, field: "trigger")}</td>
					
						<td>${fieldValue(bean: scenarioInstance, field: "useCase")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scenarioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
