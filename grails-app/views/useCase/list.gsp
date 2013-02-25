
<%@ page import="testgrails.UseCase" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'useCase.label', default: 'UseCase')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-useCase" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-useCase" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'useCase.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="goal" title="${message(code: 'useCase.goal.label', default: 'Goal')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'useCase.name.label', default: 'Name')}" />
					
						<th><g:message code="useCase.release.label" default="Release" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${useCaseInstanceList}" status="i" var="useCaseInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${useCaseInstance.id}">${fieldValue(bean: useCaseInstance, field: "description")}</g:link></td>
					
						<td>${fieldValue(bean: useCaseInstance, field: "goal")}</td>
					
						<td>${fieldValue(bean: useCaseInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: useCaseInstance, field: "release")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${useCaseInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
