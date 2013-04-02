package testgrails

import grails.converters.JSON

class UserStoryRestController {

	def showUserStoryById() {
		def userStory = UserStory.findById(params.id, [fetch:[epic:"eager", role:"eager"]])
		if (!userStory) {
			render renderNotFound
		}
		else {
			userStory = userStory.transformToMap()
			userStory = ["user_story": userStory]
			render (contentType: "application/json", text: userStory as JSON)
		}
	}	
	
	def showAllUserStories() {		
		def all
		def ids = params["ids[]"] 
		if(ids){
			println "Received: $ids"
			all = ids.collect{id -> UserStory.get id}
		}
		else if (params.epic) {			
			all = Epic.findById(params.epic)
			if (!all) {
				render renderNotFound
				return
			}	
			else {
				all = all.getUserStories()
			}		
		} 
		else {
			all = UserStory.list()
		}
		if (all.empty) {
			render renderNotFound
		}
		else {			
			List<Map> returnMap = new ArrayList<Map>()
			all.each {
				def map = it.transformToMap()
				returnMap.add(map)
			}
			def returnedUserStories = ["user_stories": returnMap]
			render (contentType: "application/json", text: returnedUserStories as JSON)
		}
	}	
	
	def create = {
		def userStoryInstance = new UserStory()
		userStoryInstance.properties = params
		return [userStoryInstance: userStoryInstance]
	}
	
	def save = {
		def userStoryInstance = new UserStory(params)
		if (userStoryInstance.save(flush: true)) {
			response.status = 200 // OK
			userStoryInstance = userStoryInstance.transformToMap()
			userStoryInstance = ["user_story": userStoryInstance]
			render (contentType: "application/json", text: userStoryInstance as JSON)			
		}
		else {
			response.status = 400 // Bad Request
			render userStoryInstance.errors.allErrors as JSON
		}
	}
	
	def update = {
		def p = params
		println(p)
		def userStoryInstance = UserStory.get(params.id)
		if (userStoryInstance) {
			if (p.version) {
				def version = p.version.toLong()
				if (userStoryInstance.version > version) {
					userStoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userStory.label', default: 'UserStory')] as Object[], "Another user has updated this UserStory while you were editing")
					render render409.curry(userStoryInstance)
					return
				}
			}
			def props = p.user_story
			def userStoryRole = Role.get(props.role_id)
			if (userStoryRole) {
				userStoryInstance.role = userStoryRole
			}
			else {
				userStoryInstance.role = null
			}
			userStoryInstance.properties = p.user_story			
			if (!userStoryInstance.hasErrors() && userStoryInstance.save(flush: true)) {
				response.status = 200 // OK
				userStoryInstance = userStoryInstance.transformToMap()
				userStoryInstance = ["user_story": userStoryInstance]
				render (contentType: "application/json", text: userStoryInstance as JSON)				
			}
			else {
				render render409.curry(userStoryInstance)
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def delete = {
		def userStoryInstance = UserStory.get(params.id)
		if (userStoryInstance) {
			try {
				userStoryInstance.delete(flush: true)
				response.status = 200 // OK
				render "${message(code: 'default.deleted.message', args: [message(code: 'userStory.label', default: 'UserStory'), params.id])}"
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'userStory.label', default: 'UserStory'), params.id])}"
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def renderNotFound = {
		response.status = 404
		if (!params.id) {
			render "No UserStories were found"
		}
		else {
			render "UserStory ${params.id} not found."
		}
	}
	
	def render409 = { userStoryInstance ->
		response.status = 409 // Conflict
		render userStoryInstance.errors.allErrors as JSON
	}
}
