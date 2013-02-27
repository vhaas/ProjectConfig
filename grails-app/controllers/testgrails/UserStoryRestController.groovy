package testgrails

import grails.converters.XML

class UserStoryRestController {

    def index() { }
	
	def showUserStoryById() {
		if (params.id && UserStory.exists(params.id)) {
			def p = UserStory.findByName(params.id)
			render p as XML
		}
		else {
			def all = UserStory.list()
			render all as XML
		}
	}
	
	def showAllUserStories() {
		def all = UserStory.list()
		render all as XML
	}
}
