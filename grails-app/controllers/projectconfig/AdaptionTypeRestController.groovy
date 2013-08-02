package projectconfig

import grails.converters.JSON

class AdaptionTypeRestController {

	def showAdaptionTypeById() {
		def adaptionType = AdaptionType.get(params.id)
		if (!adaptionType) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(AdaptionType, adaptionType)
		}
	}
	
	def showAllAdaptionTypes() {
		def all
		def ids = params["ids[]"]
		if(ids){
			all = ids.collect{id -> AdaptionType.get id}
		}
		else if (params.project) {
			all = Project.findById(params.project)
			if (!all) {
				render renderNotFound
				return
			}
			else {
				all = all.getAdaptionTypes()
			}
		}
		else {
			all = AdaptionType.list()
		}
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(AdaptionType, all.asList())
		}
	}
	
	def save = {
		def adaptionTypeInstance = new AdaptionType()
		def props = params.adaption_type
		adaptionTypeInstance.properties = props
		def adaptionTypeProject = Project.get(props.project_id)
		if (adaptionTypeProject) {
			adaptionTypeInstance.project = adaptionTypeProject
		}
		else {
			adaptionTypeInstance.project = null
		}
		if (adaptionTypeInstance.save(flush: true)) {
			response.status = 200 // OK
			render RestControllerAssistant.renderSingle(AdaptionType, adaptionTypeInstance)
		}
		else {
			response.status = 400 // Bad Request
			render adaptionTypeInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		def adaptionTypeInstance = AdaptionType.get(params.id)
		if (adaptionTypeInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (adaptionTypeInstance.version > version) {
					adaptionTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'adaptionType.label', default: 'AdaptionType')] as Object[], "Another user has updated this AdaptionType while you were editing")
					render render409.curry(adaptionTypeInstance)
					return
				}
			}
			adaptionTypeInstance.properties = p.adaption_type
			if (!adaptionTypeInstance.hasErrors() && adaptionTypeInstance.save(flush: true)) {
				response.status = 200 // OK
				render RestControllerAssistant.renderSingle(AdaptionType, adaptionTypeInstance)
			}
			else {
				render render409.curry(adaptionTypeInstance)
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def delete = {
		def adaptionTypeInstance = AdaptionType.get(params.id)
		if (adaptionTypeInstance) {
			try {
				adaptionTypeInstance.delete(flush: true)
				response.status = 200 // OK
				render null
//				render "${message(code: 'default.deleted.message', args: [message(code: 'role.label', default: 'Role'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'adaptionType.label', default: 'AdaptionType'), params.id])}"
			}
		}
		else {
			render renderNotFound
		}
	}

	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No AdaptionTypes were found"
		}
		else {
			render "AdaptionType ${params.id} not found."
		}
	}

	def render409 = { adaptionTypeInstance ->
		response.status = 409 // Conflict
		render adaptionTypeInstance.errors.allErrors as JSON
	}
}
