
<%@ page import="testgrails.EffortEstimate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'effortEstimate.label', default: 'EffortEstimate')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-effortEstimate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-effortEstimate" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list effortEstimate">
			
				<g:if test="${effortEstimateInstance?.effortType}">
				<li class="fieldcontain">
					<span id="effortType-label" class="property-label"><g:message code="effortEstimate.effortType.label" default="Effort Type" /></span>
					
						<span class="property-value" aria-labelledby="effortType-label"><g:fieldValue bean="${effortEstimateInstance}" field="effortType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.firstEffortEstimate}">
				<li class="fieldcontain">
					<span id="firstEffortEstimate-label" class="property-label"><g:message code="effortEstimate.firstEffortEstimate.label" default="First Effort Estimate" /></span>
					
						<span class="property-value" aria-labelledby="firstEffortEstimate-label"><g:link controller="firstEffortEstimate" action="show" id="${effortEstimateInstance?.firstEffortEstimate?.id}">${effortEstimateInstance?.firstEffortEstimate?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.maxEffort}">
				<li class="fieldcontain">
					<span id="maxEffort-label" class="property-label"><g:message code="effortEstimate.maxEffort.label" default="Max Effort" /></span>
					
						<span class="property-value" aria-labelledby="maxEffort-label"><g:fieldValue bean="${effortEstimateInstance}" field="maxEffort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.medEffort}">
				<li class="fieldcontain">
					<span id="medEffort-label" class="property-label"><g:message code="effortEstimate.medEffort.label" default="Med Effort" /></span>
					
						<span class="property-value" aria-labelledby="medEffort-label"><g:fieldValue bean="${effortEstimateInstance}" field="medEffort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.minEffort}">
				<li class="fieldcontain">
					<span id="minEffort-label" class="property-label"><g:message code="effortEstimate.minEffort.label" default="Min Effort" /></span>
					
						<span class="property-value" aria-labelledby="minEffort-label"><g:fieldValue bean="${effortEstimateInstance}" field="minEffort"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.moduleChanges}">
				<li class="fieldcontain">
					<span id="moduleChanges-label" class="property-label"><g:message code="effortEstimate.moduleChanges.label" default="Module Changes" /></span>
					
						<g:each in="${effortEstimateInstance.moduleChanges}" var="m">
						<span class="property-value" aria-labelledby="moduleChanges-label"><g:link controller="moduleChange" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.neededProfiles}">
				<li class="fieldcontain">
					<span id="neededProfiles-label" class="property-label"><g:message code="effortEstimate.neededProfiles.label" default="Needed Profiles" /></span>
					
						<g:each in="${effortEstimateInstance.neededProfiles}" var="n">
						<span class="property-value" aria-labelledby="neededProfiles-label"><g:link controller="profile" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.plannedEfforts}">
				<li class="fieldcontain">
					<span id="plannedEfforts-label" class="property-label"><g:message code="effortEstimate.plannedEfforts.label" default="Planned Efforts" /></span>
					
						<g:each in="${effortEstimateInstance.plannedEfforts}" var="p">
						<span class="property-value" aria-labelledby="plannedEfforts-label"><g:link controller="plannedEffort" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${effortEstimateInstance?.risk}">
				<li class="fieldcontain">
					<span id="risk-label" class="property-label"><g:message code="effortEstimate.risk.label" default="Risk" /></span>
					
						<span class="property-value" aria-labelledby="risk-label"><g:fieldValue bean="${effortEstimateInstance}" field="risk"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${effortEstimateInstance?.id}" />
					<g:link class="edit" action="edit" id="${effortEstimateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
