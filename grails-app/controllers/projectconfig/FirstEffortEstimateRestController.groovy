package projectconfig

import grails.converters.JSON

class FirstEffortEstimateRestController {

	def showFirstEffortEstimateById() {
		def firstEffortEstimate = FirstEffortEstimate.findById(params.id)
		if (!firstEffortEstimate) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(FirstEffortEstimate, firstEffortEstimate)
		}
	}
	
	def showAllFirstEffortEstimates() {
		def all
		def ids = params["ids[]"]
		if(ids){
			all = ids.collect{id -> FirstEffortEstimate.get id}
		}
		else if (params.project) {
			all = Project.findById(params.project)
			if (!all) {
				render renderNotFound
				return
			}
			else {
				all = all.getFirstEffortEstimates()
			}
		}		
		else {
			all = FirstEffortEstimate.list()
		}
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(FirstEffortEstimate, all.asList())
		}
	}
	
	def save = {
		def firstEffortEstimateInstance = new FirstEffortEstimate()
		def props = params.user_story
		firstEffortEstimateInstance.properties = params.first_effort_estimate
		def firstEffortEstimateProject = Project.get(props.project_id)
		if (firstEffortEstimateProject) {
			firstEffortEstimateInstance.project = firstEffortEstimateProject
		}		
		else {
			firstEffortEstimateInstance.project = null
		}
		if (firstEffortEstimateInstance.save(flush: true)) {
			response.status = 200 // OK
			render RestControllerAssistant.renderSingle(FirstEffortEstimate, firstEffortEstimateInstance)
		}
		else {
			response.status = 400 // Bad Request
			render firstEffortEstimateInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		def firstEffortEstimateInstance = FirstEffortEstimate.get(params.id)
		if (firstEffortEstimateInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (firstEffortEstimateInstance.version > version) {
					firstEffortEstimateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate')] as Object[], "Another user has updated this FirstEffortEstimate while you were editing")
					render render409.curry(firstEffortEstimateInstance)
					return
				}
			}
			def props = p.first_effort_estimate
			def paramSystemChange = SystemChange.get(props.system_change_id)
			if (paramSystemChange) {
				firstEffortEstimateInstance.systemChange = paramSystemChange
			}
			else {
				firstEffortEstimateInstance.systemChange = null
			}									
			firstEffortEstimateInstance.properties = p.first_effort_estimate
			if (!firstEffortEstimateInstance.hasErrors() && firstEffortEstimateInstance.save(flush: true)) {
				response.status = 200 // OK
				render RestControllerAssistant.renderSingle(FirstEffortEstimate, firstEffortEstimateInstance)
			}
			else {
				render render409.curry(firstEffortEstimateInstance)
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def delete = {
		def firstEffortEstimateInstance = FirstEffortEstimate.get(params.id)
		if (firstEffortEstimateInstance) {
			try {
				firstEffortEstimateInstance.delete(flush: true)
				response.status = 200 // OK
				render null
//				render "${message(code: 'default.deleted.message', args: [message(code: 'userStory.label', default: 'UserStory'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), params.id])}"
			}
		}
		else {
			render renderNotFound
		}
	}

	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No FirstEffortEstimates were found"
		}
		else {
			render "FirstEffortEstimate ${params.id} not found."
		}
	}

	def render409 = { firstEffortEstimateInstance ->
		response.status = 409 // Conflict
		render firstEffortEstimateInstance.errors.allErrors as JSON
	}
}
