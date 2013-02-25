
<%@ page import="testgrails.RoadMap" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'roadMap.label', default: 'RoadMap')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-roadMap" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-roadMap" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="date" title="${message(code: 'roadMap.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'roadMap.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="orderId" title="${message(code: 'roadMap.orderId.label', default: 'Order Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${roadMapInstanceList}" status="i" var="roadMapInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${roadMapInstance.id}">${fieldValue(bean: roadMapInstance, field: "date")}</g:link></td>
					
						<td>${fieldValue(bean: roadMapInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: roadMapInstance, field: "orderId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${roadMapInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
