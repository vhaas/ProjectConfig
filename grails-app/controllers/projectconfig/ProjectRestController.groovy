package projectconfig

import grails.converters.JSON


class ProjectRestController {

	def showProjectById() {
		def project = Project.get(params.id)
		if (!project) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(Project, project)
		}
	}

	def showAllProjects() {
		def all
		def ids = params["ids[]"]
		if(ids){
			all = ids.collect{id -> Project.get id}
		}
		else {
			all = Project.list()
		}
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(Project, all)
		}
	}

	def save = {
		def projectInstance = new Project(params)
		if (projectInstance.save(flush: true)) {
			response.status = 200 // OK
			render RestControllerAssistant.renderSingle(Project, projectInstance)
		}
		else {
			response.status = 400 // Bad Request
			render projectInstance.errors.allErrors as JSON
		}
	}

	def update = {
		def p = params
		def projectInstance = Project.get(params.id)
		if (projectInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (projectInstance.version > version) {
					projectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'project.label', default: 'Project')] as Object[], "Another user has updated this UserStory while you were editing")
					render render409.curry(projectInstance)
					return
				}
			}
			projectInstance.properties = p.project
			if (!projectInstance.hasErrors() && projectInstance.save(flush: true)) {
				response.status = 200 // OK
				render RestControllerAssistant.renderSingle(Project, projectInstance)
			}
			else {
				render render409.curry(projectInstance)
			}
		}
		else {
			render renderNotFound
		}
	}

	def delete = {
		def epicInstance = Epic.get(params.id)
		if (epicInstance) {
			try {
				epicInstance.delete(flush: true)
				response.status = 200 // OK
				render null
//				render "${message(code: 'default.deleted.message', args: [message(code: 'epic.label', default: 'Epic'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'epic.label', default: 'Epic'), params.id])}"
			}
		}
		else {
			render renderNotFound
		}
	}

	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No Projects were found"
		}
		else {
			render "Project ${params.id} not found."
		}
	}

	def render409 = { projectInstance ->
		response.status = 409 // Conflict
		render projectInstance.errors.allErrors as JSON
	}
}