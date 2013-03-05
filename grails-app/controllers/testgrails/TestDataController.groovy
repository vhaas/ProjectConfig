package testgrails

import grails.converters.JSON

class TestDataController {

    def grailsApplication // get grails app injected into test
    
    def testBuildAllDomains() {
        def successful = true
		def project = Project.build()
		println(project.id)
		def epic = Epic.build()
        grailsApplication.domainClasses.each { domainClass ->
            println "Test of ${domainClass.name}.build()"
            try {
				for (i in 0..9) {	
//					if (!domainClass.name == "Project") {				
						def domainObject = domainClass.clazz.build(project: project)
						if (!domainObject.getClass().getSimpleName().toString() == "Project") {
							domainObject.project = project
						}
						println(domainObject.getClass().getSimpleName().toString())
						if (domainObject.getClass().getSimpleName().toString() == "UserStory") {
							def userStory = UserStory.build(epic: epic)
							if (!userStory)
								println("maybe next time")
						}					
		//	            assertNotNull domainObject."${domainClass.identifier.name}"
		                println "********** SUCCESSFUL BUILD OF $domainClass"
						domainObject.save(flush: true)
//					}
				}
            } catch (Exception e) {
                println "********** FAILED BUILD OF $domainClass"
                successful = false
            }
        }
        if (successful) {
			render "this worked out"
		}
		else {
			render "didnt do what it was told to..."
		}
    }
}
