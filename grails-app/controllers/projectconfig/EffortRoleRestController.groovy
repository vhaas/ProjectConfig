package projectconfig

import grails.converters.JSON

class EffortEffortRoleRestController {

	def showEffortRoleById() {
		def effortRole = EffortRole.get(params.id)
		if (!effortRole) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(EffortRole, effortRole)
		}
	}

	def showAllEffortRoles() {
		def all
		def ids = params["ids[]"]
		if(ids){
			all = ids.collect{id -> EffortRole.get id}
		}
		else if (params.first_effort_estimate) {
			all = FirstEffortEstimate.findById(params.first_effort_estimate)
			if (!all) {
				render renderNotFound
				return
			}
			else {
				all = all.getEffortRoles()
			}
		}
		else {
			all = EffortRole.list()
		}
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(EffortRole, all.asList())
		}
	}

	def save = {
		def effortRoleInstance = new EffortRole()
		def props = params.effort_role
		effortRoleInstance.properties = props
		if (effortRoleInstance.save(flush: true)) {
			response.status = 200 // OK
			render RestControllerAssistant.renderSingle(EffortRole, effortRoleInstance)
		}
		else {
			response.status = 400 // Bad Request
			render effortRoleInstance.errors.allErrors as JSON
		}
	}

	def update = {
		def p = params
		def effortRoleInstance = EffortRole.get(params.id)
		if (effortRoleInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (effortRoleInstance.version > version) {
					effortRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'effortRole.label', default: 'EffortRole')] as Object[], "Another user has updated this EffortRole while you were editing")
					render render409.curry(effortRoleInstance)
					return
				}
			}
			effortRoleInstance.properties = p.effortRole
			if (!effortRoleInstance.hasErrors() && effortRoleInstance.save(flush: true)) {
				response.status = 200 // OK
				render RestControllerAssistant.renderSingle(EffortRole, effortRoleInstance)
			}
			else {
				render render409.curry(effortRoleInstance)
			}
		}
		else {
			render renderNotFound
		}
	}

	def delete = {
		def effortRoleInstance = EffortRole.get(params.id)
		if (effortRoleInstance) {
			try {
				effortRoleInstance.delete(flush: true)
				response.status = 200 // OK
				render null
//				render "${message(code: 'default.deleted.message', args: [message(code: 'effortRole.label', default: 'EffortRole'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'effortRole.label', default: 'EffortRole'), params.id])}"
			}
		}
		else {
			render renderNotFound
		}
	}

	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No EffortRoles were found"
		}
		else {
			render "EffortRole ${params.id} not found."
		}
	}

	def render409 = { effortRoleInstance ->
		response.status = 409 // Conflict
		render effortRoleInstance.errors.allErrors as JSON
	}
}
