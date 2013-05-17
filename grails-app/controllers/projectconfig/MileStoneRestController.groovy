package projectconfig

import grails.converters.JSON

class MileStoneRestController {

    def showMileStoneById() {
		def mileStone = MileStone.get(params.id)
		if (!mileStone) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderSingle(MileStone, mileStone)
			
			//render (contentType: "application/json", text: RestControllerAssistant.renderSingle(Project, project))
		}
	}
	
	def showAllMileStones() {
		def all = MileStone.list()
		if (all.empty) {
			render renderNotFound
		}
		else {
			render RestControllerAssistant.renderMultiple_alternative(MileStone, all)
			
			//render (contentType: "application/json", text: RestControllerAssistant.renderMultiple_alternative(Project, all))
		}
	}
	
	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No mileStones were found"
		}
		else {
			render "mileStone ${params.id} not found."
		}
	}
	
	def render409 = { mileStoneInstance ->
		response.status = 409 // Conflict
		render mileStoneInstance.errors.allErrors as JSON
	}
}
