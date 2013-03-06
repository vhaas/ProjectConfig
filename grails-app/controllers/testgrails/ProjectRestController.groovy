package testgrails

import grails.converters.JSON


class ProjectRestController {
	
	def showProjectById() {
		def project = Project.get(params.id)
		if (!project) {
			render renderNotFound
		}
		else {			
			project = project.transformToMap()
			project = ["project": project]			
			render (contentType: "application/json", text: project as JSON)
		}
	}
	
	def showAllProjects() {
		def all = Project.list()
		if (all.empty) {
			render renderNotFound
		}
		else {
			List<Map> returnMap = new ArrayList<Map>()
			all.each {
				def map = it.transformToMap()
				returnMap.add(map)
			}
			def returnedProjects = ["projects": returnMap]
			render (contentType: "application/json", text: returnedProjects as JSON)
		}
	}
	
	def create = {
		def projectInstance = new Project()
		projectInstance.properties = params
		return [projectInstance: projectInstance]
	}
	
	def save = {
		def projectInstance = new Project(params)
		if (projectInstance.save(flush: true)) {
			response.status = 200 // OK
			projectInstance = projectInstance.transformToMap()
			projectInstance = ["project": projectInstance]
			render (contentType: "application/json", text: projectInstance as JSON)			
		}
		else {
			response.status = 400 // Bad Request
			render projectInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		println(p)
		def projectInstance = Project.get(params.id)
		if (projectInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (projectInstance.version > version) {
					projectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'project.label', default: 'Project')] as Object[], "Another user has updated this UserStory while you were editing")
					render render409.curry(projectInstance)
					return
				}
			}
			projectInstance.properties = p.project
			if (!projectInstance.hasErrors() && projectInstance.save(flush: true)) {
				response.status = 200 // OK
				projectInstance = projectInstance.transformToMap()
				projectInstance = ["project": projectInstance]
				render (contentType: "application/json", text: projectInstance as JSON)				
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
				render "${message(code: 'default.deleted.message', args: [message(code: 'epic.label', default: 'Epic'), params.id])}"
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
