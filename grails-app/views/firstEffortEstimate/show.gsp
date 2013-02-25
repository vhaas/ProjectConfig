
<%@ page import="testgrails.FirstEffortEstimate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-firstEffortEstimate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-firstEffortEstimate" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list firstEffortEstimate">
			
				<g:if test="${firstEffortEstimateInstance?.effortEstimates}">
				<li class="fieldcontain">
					<span id="effortEstimates-label" class="property-label"><g:message code="firstEffortEstimate.effortEstimates.label" default="Effort Estimates" /></span>
					
						<g:each in="${firstEffortEstimateInstance.effortEstimates}" var="e">
						<span class="property-value" aria-labelledby="effortEstimates-label"><g:link controller="effortEstimate" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${firstEffortEstimateInstance?.effortType}">
				<li class="fieldcontain">
					<span id="effortType-label" class="property-label"><g:message code="firstEffortEstimate.effortType.label" default="Effort Type" /></span>
					
						<span class="property-value" aria-labelledby="effortType-label"><g:fieldValue bean="${firstEffortEstimateInstance}" field="effortType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${firstEffortEstimateInstance?.maxEffort}">
				<li class="fieldcontain">
					<span id="maxEffort-label" class="property-label"><g:message code="firstEffortEstimate.maxEffort.label" default="Max Effort" /></span>
					
						<span class="property-value" aria-labelledby="maxEffort-label"><g:fieldValue bean="${firstEffortEstimateInstance}" field="maxEffort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${firstEffortEstimateInstance?.medEffort}">
				<li class="fieldcontain">
					<span id="medEffort-label" class="property-label"><g:message code="firstEffortEstimate.medEffort.label" default="Med Effort" /></span>
					
						<span class="property-value" aria-labelledby="medEffort-label"><g:fieldValue bean="${firstEffortEstimateInstance}" field="medEffort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${firstEffortEstimateInstance?.minEffort}">
				<li class="fieldcontain">
					<span id="minEffort-label" class="property-label"><g:message code="firstEffortEstimate.minEffort.label" default="Min Effort" /></span>
					
						<span class="property-value" aria-labelledby="minEffort-label"><g:fieldValue bean="${firstEffortEstimateInstance}" field="minEffort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${firstEffortEstimateInstance?.risk}">
				<li class="fieldcontain">
					<span id="risk-label" class="property-label"><g:message code="firstEffortEstimate.risk.label" default="Risk" /></span>
					
						<span class="property-value" aria-labelledby="risk-label"><g:fieldValue bean="${firstEffortEstimateInstance}" field="risk"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${firstEffortEstimateInstance?.systemChanges}">
				<li class="fieldcontain">
					<span id="systemChanges-label" class="property-label"><g:message code="firstEffortEstimate.systemChanges.label" default="System Changes" /></span>
					
						<g:each in="${firstEffortEstimateInstance.systemChanges}" var="s">
						<span class="property-value" aria-labelledby="systemChanges-label"><g:link controller="systemChange" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${firstEffortEstimateInstance?.id}" />
					<g:link class="edit" action="edit" id="${firstEffortEstimateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
