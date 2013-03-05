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
	
	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No Projects were found"
		}
		else {
			render "Project ${params.id} not found."
		}
	}

    
}
