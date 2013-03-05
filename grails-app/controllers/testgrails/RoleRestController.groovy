package testgrails

import grails.converters.JSON

class RoleRestController {
	
	def showRoleById() {
		def role = Role.get(params.id)
		if (!role) {
			render renderNotFound
		} 
		else {
			role = role.transformToMap()
			role = ["role": role]
			render (contentType: "application/json", text: role as JSON)
		}		
	}
	
	def showAllRoles() {
		def all = Role.list()
		if (all.empty) {
			render renderNotFound
		}
		else {
			List<Map> returnMap = new ArrayList<Map>()
			all.each {
				def map = it.transformToMap()
				returnMap.add(map)
			}
			def returnedRoles = ["roles": returnMap]
			render (contentType: "application/json", text: returnedRoles as JSON)
		}
	}
	
	def create = {
		def roleInstance = new Role()
		roleInstance.properties = params
		return [roleInstance: roleInstance]
	}
	
	def save = {
		def roleInstance = new Role(params)
		if (roleInstance.save(flush: true)) {
			response.status = 200 // OK
			render roleInstance as JSON			
		}
		else {
			response.status = 400 // Bad Request
			render roleInstance.errors.allErrors as JSON				
		}
	}
	
	def update = {
		def p = params
		println(p)
		def roleInstance = Role.get(params.id)
	    if (roleInstance) {
	        if (p.version) {
	            def version = p.version.toLong()
	            if (roleInstance.version > version) {
	                roleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'role.label', default: 'Role')] as Object[], "Another user has updated this Role while you were editing")
	                render render409.curry(roleInstance)
	                return
	            }
	        }
	        roleInstance.properties = p.role
	        if (!roleInstance.hasErrors() && roleInstance.save(flush: true)) {	               
                response.status = 200 // OK
                render roleInstance as JSON				
	        }
	        else {
	            render render409.curry(roleInstance)
	        }
	    }
	    else {
	        render renderNotFound
	    }
	}
	
	def delete = {
		def roleInstance = Role.get(params.id)
		if (roleInstance) {
			try {
				roleInstance.delete(flush: true)				
				response.status = 200 // OK
				render "${message(code: 'default.deleted.message', args: [message(code: 'role.label', default: 'Role'), params.id])}"			
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {				
				response.status = 409 // Conflict
				render "${message(code: 'default.not.deleted.message', args: [message(code: 'role.label', default: 'Role'), params.id])}"									
			}
		}
		else {
			render renderNotFound
		}
	}
	
	def renderNotFound = {		
		response.status = 404
		if (!params.id) {
			render "No Roles were found"
		} 
		else {
			render "Role ${params.id} not found."
		}		
	}
	
	def render409 = { roleInstance ->			
		response.status = 409 // Conflict
		render roleInstance.errors.allErrors as JSON				
	}	
}
