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
		}
	}

	def showAllMileStones() {
		def all
		def ids = params["ids[]"]
		if (ids) {
			all = ids.collect{id -> MileStone.get id}
		}
		else if (params.roadmap) {
			all = RoadMap.findById(params.roadmap)
			if (!all) {
				render renderNotFound
				return 
			}
			else {
				all = all.getMileStones()
			}
		}
		else {
			all = MileStone.list()
		}
		if (all.empty) {
			render renderNotFound
		} else {
			render RestControllerAssistant.renderMultiple_alternative(MileStone, all.asList())
		}
	}
	
	def save = {
		def milestoneInstance = new MileStone()
		def props = params.mile_stone
		milestoneInstance.properties = params.mile_stone
		milestoneInstance.project = Project.get(props.project_id)
		def roadmap = RoadMap.get(props.road_map_id)
		milestoneInstance.roadMap = roadmap
		def mileStones = MileStone.findAllByRoadMap(roadmap, [sort: 'orderId', order: 'desc'])
		if (mileStones.empty) {
			milestoneInstance.orderId = 1
		} else {
			milestoneInstance.orderId = mileStones.get(0).orderId + 1
		}
		if (milestoneInstance.save(flush: true)) {
			response.status = 200 //OK
			milestoneInstance = milestoneInstance.transformToMap()
			milestoneInstance = ["mile_stone": milestoneInstance]
			render (contentType: "application/json", text: milestoneInstance as JSON)
		}
		else {
			response.status = 400 // Bad Request
			render milestoneInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		def milestoneInstance = MileStone.get(params.id)
		if (milestoneInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (milestoneInstance.version > version) {
					milestoneInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'milestone.label', default: 'MileStone')] as Object[], "Another user has updated this MileStone while you were editing")
					render render409.curry(milestoneInstance)
					return
				}
			}
			milestoneInstance.properties = p.mile_stone
			if (!milestoneInstance.hasErrors() && milestoneInstance.save(flush: true)) {
				response.status = 200 // OK
				milestoneInstance = milestoneInstance.transformToMap()
				milestoneInstance = ["mile_stone": milestoneInstance]
				render (contentType: "application/json", text: milestoneInstance as JSON)
			}
			else {
				render render409.curry(milestoneInstance)
			}
		}
		else {
			render renderNotFound
		}
	}

	def delete = {
		def milestoneInstance = MileStone.get(params.id)
		if (milestoneInstance) {
			try {
				milestoneInstance.delete(flush: true)
				response.status = 200 // OK
				render "${message(code: 'default.deleted.message', args: [message(code: 'milestone.label', default: 'MileStone'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'milestone.label', default: 'MileStone'), params.id])}"
			}
		}
		else {
			render renderNotFound
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