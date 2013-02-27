package testgrails

import grails.converters.XML

class EpicRestController {

    def index() { }
	
	def showEpicById() {
		if (params.id && Epic.exists(params.id)) {
			def p = Epic.findByName(params.id)
						
			render p as XML
		}
		else {
			def all = Epic.list()
			render all as XML
		}
	}
}
