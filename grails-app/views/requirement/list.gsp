
<%@ page import="testgrails.Epic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">		
	</head>
	<body>	
		<div id="status" role="complementary">
			<ul>
				<li>
					<div id="list-epic" class="content scaffold-list" role="main">
						<h1><g:message code="default.list.label" args="[entityName]" /></h1>
						<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
						</g:if>
						<table>
							<thead>
								<tr>
								
									<g:sortableColumn property="description" title="${message(code: 'epic.description.label', default: 'Description')}" />
								
									<g:sortableColumn property="name" title="${message(code: 'epic.name.label', default: 'Name')}" />
								
								</tr>
							</thead>
							<tbody>
							<g:each in="${epicInstanceList}" status="i" var="epicInstance">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								
									<td><g:link action="show" id="${epicInstance.id}">${fieldValue(bean: epicInstance, field: "description")}</g:link></td>
								
									<td>${fieldValue(bean: epicInstance, field: "name")}</td>
								
								</tr>
							</g:each>
							</tbody>
						</table>
						<div class="pagination">
							<g:paginate total="${epicInstanceTotal}" />
						</div>
					</div>
				</li>
			</ul>
		</div>		
	</body>
</html>
