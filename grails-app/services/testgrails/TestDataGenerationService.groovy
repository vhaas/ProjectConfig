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
	
	def buildCustomizedTestData() {
		Project projectA = new Project(name: "Project A", description: "Description for Project A")
		Project projectB = new Project(name: "Project B", description: "Description for Project B")
		
		Epic epicAA = new Epic(name: "Epic A", description: "Description of Epic A", project: projectA)
		Epic epicAB = new Epic(name: "Epic B", description: "Description of Epic B", project: projectB)
		projectA.addToEpics(epicAA)
		projectA.addToEpics(epicAB)
		
		
		Epic epicBA = new Epic(name: "Epic A", description: "Description of Epic A", project: projectA)
		Epic epicBB = new Epic(name: "Epic B", description: "Description of Epic B", project: projectB)
		projectB.addToEpics(epicBA)
		projectB.addToEpics(epicBB)
		
		UserStory storyAAA = new UserStory(name: "Story A", benefit: "Benefit ot Story A", goal: "Goal of Story A", description: "Description of Story A")
		UserStory storyAAB = new UserStory(name: "Story B", benefit: "Benefit ot Story B", goal: "Goal of Story B", description: "Description of Story B")
		epicAA.addToUserStories(storyAAA)
		epicAA.addToUserStories(storyAAB)
		
		UserStory storyABA = new UserStory(name: "Story A", benefit: "Benefit ot Story A", goal: "Goal of Story A", description: "Description of Story A")
		UserStory storyABB = new UserStory(name: "Story B", benefit: "Benefit ot Story B", goal: "Goal of Story B", description: "Description of Story B")
		epicAB.addToUserStories(storyABA)
		epicAB.addToUserStories(storyABB)

		UserStory storyBAA = new UserStory(name: "Story A", benefit: "Benefit ot Story A", goal: "Goal of Story A", description: "Description of Story A")
		UserStory storyBAB = new UserStory(name: "Story B", benefit: "Benefit ot Story B", goal: "Goal of Story B", description: "Description of Story B")
		epicBA.addToUserStories(storyBAA)
		epicBA.addToUserStories(storyBAB)

		UserStory storyBBA = new UserStory(name: "Story A", benefit: "Benefit ot Story A", goal: "Goal of Story A", description: "Description of Story A")
		UserStory storyBBB = new UserStory(name: "Story B", benefit: "Benefit ot Story B", goal: "Goal of Story B", description: "Description of Story B")
		epicBB.addToUserStories(storyBBA)
		epicBB.addToUserStories(storyBBB)

		storyAAA.save(failOnError: true)
		storyAAB.save(failOnError: true)
		storyABA.save(failOnError: true)
		storyABB.save(failOnError: true)
		storyBAA.save(failOnError: true)
		storyBAB.save(failOnError: true)
		storyBBA.save(failOnError: true)
		storyBBB.save(failOnError: true)
		
		projectA.save(failOnError: true)
		projectB.save(failOnError: true)

		epicAA.save(failOnError: true)
		epicAB.save(failOnError: true)
		epicBA.save(failOnError: true)
		epicBB.save(failOnError: true)

				println "Created the Epic: $epicAA"
	}
}