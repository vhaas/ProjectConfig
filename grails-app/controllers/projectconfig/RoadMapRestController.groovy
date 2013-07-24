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
	
	def save = {
		def roadmapInstance = new RoadMap()
		def props = params.road_map
		roadmapInstance.properties = params.road_map
		roadmapInstance.project = Project.get(props.project_id)
		if (roadmapInstance.save(flush: true)) {
			response.status = 200 //OK
			roadmapInstance = roadmapInstance.transformToMap()
			roadmapInstance = ["road_map": roadmapInstance]
			render (contentType: "application/json", text: roadmapInstance as JSON)
		}
		else {
			response.status = 400 // Bad Request
			render roadmapInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		def roadMapInstance = RoadMap.get(params.id)
		if (roadMapInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (roadMapInstance.version > version) {
					roadMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'roadmap.label', default: 'RoadMap')] as Object[], "Another user has updated this RoadMap while you were editing")
					render render409.curry(roadMapInstance)
					return
				}
			}
			roadMapInstance.properties = p.road_map
			if (!roadMapInstance.hasErrors() && roadMapInstance.save(flush: true)) {
				response.status = 200 // OK
				roadMapInstance = roadMapInstance.transformToMap()
				roadMapInstance = ["road_map": roadMapInstance]
				render (contentType: "application/json", text: roadMapInstance as JSON)
			}
			else {
				render render409.curry(roadMapInstance)
			}
		}
		else {
			render renderNotFound
		}
	}

	def delete = {
		def roadMapInstance = RoadMap.get(params.id)
		if (roadMapInstance) {
			try {
				roadMapInstance.delete(flush: true)
				response.status = 200 // OK
				render "${message(code: 'default.deleted.message', args: [message(code: 'roadmap.label', default: 'RoadMap'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'roadmap.label', default: 'RoadMap'), params.id])}"
			}
		}
		else {
			render renderNotFound
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
