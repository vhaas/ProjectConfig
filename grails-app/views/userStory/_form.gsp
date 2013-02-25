<%@ page import="testgrails.UserStory" %>



<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'benefit', 'error')} ">
	<label for="benefit">
		<g:message code="userStory.benefit.label" default="Benefit" />
		
	</label>
	<g:textField name="benefit" value="${userStoryInstance?.benefit}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'epics', 'error')} ">
	<label for="epics">
		<g:message code="userStory.epics.label" default="Epics" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'goal', 'error')} ">
	<label for="goal">
		<g:message code="userStory.goal.label" default="Goal" />
		
	</label>
	<g:textField name="goal" value="${userStoryInstance?.goal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="userStory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userStoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'roadMap', 'error')} required">
	<label for="roadMap">
		<g:message code="userStory.roadMap.label" default="Road Map" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="roadMap" name="roadMap.id" from="${testgrails.RoadMap.list()}" optionKey="id" required="" value="${userStoryInstance?.roadMap?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="userStory.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="role" name="role.id" from="${testgrails.Role.list()}" optionKey="id" required="" value="${userStoryInstance?.role?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'systemChanges', 'error')} ">
	<label for="systemChanges">
		<g:message code="userStory.systemChanges.label" default="System Changes" />
		
	</label>
	<g:select name="systemChanges" from="${testgrails.SystemChange.list()}" multiple="multiple" optionKey="id" size="5" value="${userStoryInstance?.systemChanges*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userStoryInstance, field: 'useCases', 'error')} ">
	<label for="useCases">
		<g:message code="userStory.useCases.label" default="Use Cases" />
		
	</label>
	<g:select name="useCases" from="${testgrails.UseCase.list()}" multiple="multiple" optionKey="id" size="5" value="${userStoryInstance?.useCases*.id}" class="many-to-many"/>
</div>

