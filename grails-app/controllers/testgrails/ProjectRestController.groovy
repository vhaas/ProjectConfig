package testgrails

import grails.converters.JSON


class ProjectRestController {
	
	def showProjectById() {
		def project = Project.get(params.id)
		if (!project) {
			render renderNotFound
		}
		else {			
			renderMaprest(project, jsonFormat,'project')			
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
