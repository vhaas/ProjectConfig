package projectconfig

import grails.converters.JSON

class SystemRestController {

    def showSystemById() {
		def system = System.get(params.id)
		if (!system) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(Syste, system)
		}
	}
	
	def showAllSystems() {
		def all
		if (ids) {
			all = ids.collect{id -> System.get id}
		}
		else if (params.project) {
			all = Project.findById(params.project)
			if (!all) {
				render renderNotFound
				return
			}
			else {
				all = all.getSystems()
			}
		}
		else {
			all = System.list()
		}
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(System, all.asList())
		}
	}
	
	def save = {
		def systemInstance = new System(params.system)		
		def props = params.system
		systemInstance.project = Project.get(props.project_id)
		if (systemInstance.save(flush: true)) {
			response.status = 200 // OK
			render RestControllerAssistant.renderSingle(Syste, system)
		}
		else {
			response.status = 400 // Bad Request
			render systemInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		def sytemInstance = System.get(params.id)
		if (sytemInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (sytemInstance.version > version) {
					sytemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'system.label', default: 'System')] as Object[], "Another user has updated this System while you were editing")
					render render409.curry(sytemInstance)
					return
				}
			}
			sytemInstance.properties = p.system
			if (!sytemInstance.hasErrors() && sytemInstance.save(flush: true)) {
				response.status = 200 // OK
				render RestControllerAssistant.renderSingle(System, sytemInstance)
			}
			else {
				render render409.curry(sytemInstance)
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def delete = {
		def sytemInstance = System.get(params.id)
		if (sytemInstance) {
			try {
				sytemInstance.delete(flush: true)
				response.status = 200 // OK
				render null
//				render "${message(code: 'default.deleted.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No Systems were found"
		}
		else {
			render "System ${params.id} not found."
		}
	}

	def render409 = { systemInstance ->
		response.status = 409 // Conflict
		render systemInstance.errors.allErrors as JSON
	}
}
