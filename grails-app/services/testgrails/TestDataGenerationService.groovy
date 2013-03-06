package testgrails

class TestDataGenerationService {
	
	def grailsApplication // get grails app injected into test
	
	def buildTestData() {
		def successful = true
		def project = Project.build()		
		def epic = Epic.build()
		grailsApplication.domainClasses.each { domainClass ->
//			println "Test of ${domainClass.name}.build()"
			try {
				for (i in 0..9) {
					def domainObject = domainClass.clazz.build(project: project)
					if (!domainObject.getClass().getSimpleName().toString() == "Project") {
						domainObject.project = project
					}					
					if (domainObject.getClass().getSimpleName().toString() == "UserStory") {
						def userStory = UserStory.build(epic: epic)						
					}
//	            	assertNotNull domainObject."${domainClass.identifier.name}"
//					println "********** SUCCESSFUL BUILD OF $domainClass"
					domainObject.save(flush: true)
				}
			} catch (Exception e) {
				println "********** FAILED BUILD OF $domainClass"
				successful = false
			}
		}
		if (successful) {
			println("TestData was successfully generated")
		}
		else {
			println("Error while creating TestData")
		}
	}
}
