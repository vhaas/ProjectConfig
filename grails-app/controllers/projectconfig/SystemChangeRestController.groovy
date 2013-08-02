package projectconfig

import grails.converters.JSON

class SystemChangeRestController {

    def showSystemChangeById() {
		def systemChange = SystemChange.get(params.id)
		if (!systemChange) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(SystemChange, systemChange)
		}
	}
	
	def showAllSystemChanges() {
		def all
		def ids = params["ids[]"]
		if (ids) {
			all = ids.collect{id -> SystemChange.get id}
		}
		else if (params.system) {
			all = System.findById(params.system)
			if (!all) {
				render renderNotFound
				return
			}
			else {
				all = all.getSystemChanges()
			}
		}
		else if (params.adaption_type) {
			all = AdaptionType.findById(params.adaption_type)
			if (!all) {
				render renderNotFound
				return
			}
			else {
				all = all.getSystemChanges()
			}
		}
		else {
			all = SystemChange.list()
		}
		if (all.empty) {
			render renderNotFound
		} else {
			render RestControllerAssistant.renderMultiple_alternative(SystemChange, all.asList())
		}
	}
	
	def save = {
		def systemChangeInstance = new SystemChange()
		def props = params.system_change
		systemChangeInstance.properties = params.system_change
		systemChangeInstance.project = Project.get(props.project_id)
		systemChangeInstance.system = System.get(props.system_id)
		systemChangeInstance.firstEffortEstimate = FirstEffortEstimate.get(props.first_effort_estimate_id)
		systemChangeInstance.adaptionType = AdaptionType.get(props.adaption_type_id)
		def firstEffortEstimate = FirstEffortEstimate.get(props.first_effort_estimate_id)
		systemChangeInstance.firstEffortEstimate = firstEffortEstimate
		if (systemChangeInstance.save(flush: true)) {
			response.status = 200 //OK
			render RestControllerAssistant.renderSingle(MileStone, systemChangeInstance)
		}
		else {
			response.status = 400 // Bad Request
			render systemChangeInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		def systemChangeInstance = SystemChange.get(params.id)
		if (systemChangeInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (systemChangeInstance.version > version) {
					systemChangeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'systemchange.label', default: 'SystemChange')] as Object[], "Another user has updated this SystemChange while you were editing")
					render render409.curry(systemChangeInstance)
					return
				}
			}
			def paramFirstEffortEstimate = FirstEffortEstimate.get(props.first_effort_estimate_id)
			if (paramFirstEffortEstimate) {
				systemChangeInstance.firstEffortEstimate = paramFirstEffortEstimate
			}
			else {
				systemChangeInstance.firstEffortEstimate = null
			}
			def paramAdaptionType = AdaptionType.get(props.adaption_type_id)
			if (paramAdaptionType) {
				systemChangeInstance.adaptionType = paramAdaptionType
			}
			else {
				systemChangeInstance.adaptionType = null
			}
			def paramUserStory = request.JSON as Map
			def userStoryIds = paramUserStory.get('system_change').get('user_story_ids')
			if (userStoryIds) {
				List<UserStory> userStories = new ArrayList<UserStory>()
				userStoryIds.each {
					def userStory = UserStory.get(it)
					userStory.systemChanges.add(systemChangeInstance)
					userStories.add(userStory)
				}
				systemChangeInstance.userStories = userStories
			}
			systemChangeInstance.properties = p.system_change
			if (!systemChangeInstance.hasErrors() && systemChangeInstance.save(flush: true)) {
				response.status = 200 // OK
				render RestControllerAssistant.renderSingle(SystemChange, systemChangeInstance)
			}
			else {
				render render409.curry(systemChangeInstance)
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def delete = {
		def systemChangeInstance = SystemChange.get(params.id)
		if (systemChangeInstance) {
			try {
				systemChangeInstance.delete(flush: true)
				response.status = 200 // OK
				render null
//				render "${message(code: 'default.deleted.message', args: [message(code: 'milestone.label', default: 'MileStone'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'systemchange.label', default: 'SystemChange'), params.id])}"
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No SystemChanges were found"
		}
		else {
			render "SystemChange ${params.id} not found."
		}
	}

	def render409 = { systemChangeInstance ->
		response.status = 409 // Conflict
		render systemChangeInstance.errors.allErrors as JSON
	}
}
