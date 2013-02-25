
<%@ page import="testgrails.Iteration" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'iteration.label', default: 'Iteration')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-iteration" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-iteration" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="date" title="${message(code: 'iteration.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'iteration.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="orderId" title="${message(code: 'iteration.orderId.label', default: 'Order Id')}" />
					
						<th><g:message code="iteration.release.label" default="Release" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${iterationInstanceList}" status="i" var="iterationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${iterationInstance.id}">${fieldValue(bean: iterationInstance, field: "date")}</g:link></td>
					
						<td>${fieldValue(bean: iterationInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: iterationInstance, field: "orderId")}</td>
					
						<td>${fieldValue(bean: iterationInstance, field: "release")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${iterationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
