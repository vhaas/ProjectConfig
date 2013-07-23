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
		}
	}

	def showAllRoadMaps() {
		def all
		if (params.project) {
			all = Project.findById(params.project)
			if (!all) {
				render renderNotFound
				return
			}
			else {
				all = all.getRoadMaps()
			}
		} else {
			all = RoadMap.list()
		}
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(RoadMap, all.asList())
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
