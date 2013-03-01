package testgrails

import grails.converters.JSON

class UserStoryRestController {

	def showUserStoryById() {
		def userStory = UserStory.findById(params.id, [fetch:[epic:"eager", role:"eager"]])
		if (!userStory) {
			render renderNotFound
		}
		else {
			JSON.use("deep")
			render userStory as JSON			
		}
	}	
	
	def showAllUserStories() {
		def all = UserStory.list()
		if (all.empty) {
			render renderNotFound
		}
		else {
		render all as JSON
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
			render userStoryInstance as JSON
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
			userStoryInstance.properties = p.userStory
			if (!userStoryInstance.hasErrors() && userStoryInstance.save(flush: true)) {
				response.status = 200 // OK
				render userStoryInstance as JSON
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
