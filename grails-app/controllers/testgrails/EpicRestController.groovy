package testgrails

import java.util.List;

import grails.converters.JSON
import org.hibernate.transform.DistinctRootEntityResultTransformer

class EpicRestController {  		
	
	def showEpicById() {
		def epic = Epic.get(params.id)
		if (!epic) {
			render renderNotFound
		}
		else {
			epic = epic.transformToMap()
			epic = ["epic": epic]
			render (contentType: "application/json", text: epic as JSON)			
		}
	}
	
	def showAllEpics() {
		def all = Epic.list()
		if (all.empty) {
			render renderNotFound
		}
		else {
			List<Map> returnMap = new ArrayList<Map>()					
			all.each {
				def map = it.transformToMap()				
				returnMap.add(map)
			}			
			def epics = ["epics": returnMap]
			render (contentType: "application/json", text: epics as JSON)
		}
	}	
	
	def create = {
		def epicInstance = new Epic()
		epicInstance.properties = params
		return [epicInstance: epicInstance]
	}
	
	def save = {
		def epicInstance = new Epic(params)
		if (epicInstance.save(flush: true)) {
			response.status = 200 // OK
			epicInstance = epicInstance.transformToMap()
			epicInstance = ["epic": epicInstance]
			render (contentType: "application/json", text: epicInstance as JSON)			
		}
		else {
			response.status = 400 // Bad Request
			render epicInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		def epicInstance = Epic.get(params.id)
		if (epicInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (epicInstance.version > version) {
					epicInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'epic.label', default: 'Epic')] as Object[], "Another user has updated this Epic while you were editing")
					render render409.curry(epicInstance)
					return
				}
			}
			epicInstance.properties = p.epic
			if (!epicInstance.hasErrors() && epicInstance.save(flush: true)) {
				response.status = 200 // OK
				epicInstance = epicInstance.transformToMap()
				epicInstance = ["epic": epicInstance]
				render (contentType: "application/json", text: epicInstance as JSON)				
			}
			else {
				render render409.curry(epicInstance)
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
			render "No Epics were found"
		}
		else {
			render "Epic ${params.id} not found."
		}
	}
	
	def render409 = { epicInstance ->
		response.status = 409 // Conflict
		render epicInstance.errors.allErrors as JSON
	}	
}
