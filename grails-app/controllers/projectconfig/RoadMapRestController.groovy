package projectconfig

import grails.converters.JSON

class RoadMapRestController {

    def showRoadMapById() {
		def roadMap = RoadMap.get(params.id)
		if (!roadMap) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(RoadMap, roadMap)
			
			//render (contentType: "application/json", text: RestControllerAssistant.renderSingle(Project, project))
		}
	}
	
	def showAllRoadMaps() {
		def all = RoadMap.list()
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(RoadMap, all)
			
			//render (contentType: "application/json", text: RestControllerAssistant.renderMultiple_alternative(Project, all))
		}
	}
	
	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No RoadMaps were found"
		}
		else {
			render "RoadMap ${params.id} not found."
		}
	}
	
	def render409 = { roadMapInstance ->
		response.status = 409 // Conflict
		render roadMapInstance.errors.allErrors as JSON
	}
}
