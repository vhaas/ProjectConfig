<%@ page import="testgrails.UseCase" %>



<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'actors', 'error')} ">
	<label for="actors">
		<g:message code="useCase.actors.label" default="Actors" />
		
	</label>
	<g:select name="actors" from="${testgrails.Actor.list()}" multiple="multiple" optionKey="id" size="5" value="${useCaseInstance?.actors*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="useCase.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${useCaseInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'extendUseCases', 'error')} ">
	<label for="extendUseCases">
		<g:message code="useCase.extendUseCases.label" default="Extend Use Cases" />
		
	</label>
	<g:select name="extendUseCases" from="${testgrails.UseCase.list()}" multiple="multiple" optionKey="id" size="5" value="${useCaseInstance?.extendUseCases*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'goal', 'error')} ">
	<label for="goal">
		<g:message code="useCase.goal.label" default="Goal" />
		
	</label>
	<g:textField name="goal" value="${useCaseInstance?.goal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'includedUseCases', 'error')} ">
	<label for="includedUseCases">
		<g:message code="useCase.includedUseCases.label" default="Included Use Cases" />
		
	</label>
	<g:select name="includedUseCases" from="${testgrails.UseCase.list()}" multiple="multiple" optionKey="id" size="5" value="${useCaseInstance?.includedUseCases*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="useCase.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${useCaseInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'preConditions', 'error')} ">
	<label for="preConditions">
		<g:message code="useCase.preConditions.label" default="Pre Conditions" />
		
	</label>
	<g:select name="preConditions" from="${testgrails.PreConditions.list()}" multiple="multiple" optionKey="id" size="5" value="${useCaseInstance?.preConditions*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'release', 'error')} required">
	<label for="release">
		<g:message code="useCase.release.label" default="Release" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="release" name="release.id" from="${testgrails.Release.list()}" optionKey="id" required="" value="${useCaseInstance?.release?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'requiredUseCases', 'error')} ">
	<label for="requiredUseCases">
		<g:message code="useCase.requiredUseCases.label" default="Required Use Cases" />
		
	</label>
	<g:select name="requiredUseCases" from="${testgrails.UseCase.list()}" multiple="multiple" optionKey="id" size="5" value="${useCaseInstance?.requiredUseCases*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'scenarios', 'error')} ">
	<label for="scenarios">
		<g:message code="useCase.scenarios.label" default="Scenarios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${useCaseInstance?.scenarios?}" var="s">
    <li><g:link controller="scenario" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="scenario" action="create" params="['useCase.id': useCaseInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'scenario.label', default: 'Scenario')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: useCaseInstance, field: 'userStories', 'error')} ">
	<label for="userStories">
		<g:message code="useCase.userStories.label" default="User Stories" />
		
	</label>
	
</div>

