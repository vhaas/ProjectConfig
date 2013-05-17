package projectconfig

import projectconfig.Epic;
import projectconfig.Project;
import projectconfig.Role;
import projectconfig.UserStory;

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
		
		Epic epicAA = new Epic(name: "Epic AA", description: "Description of Epic AA", project: projectA)
		Epic epicAB = new Epic(name: "Epic AB", description: "Description of Epic AB", project: projectB)
		projectA.addToEpics(epicAA)
		projectA.addToEpics(epicAB)
		
		
		Epic epicBA = new Epic(name: "Epic BA", description: "Description of Epic BA", project: projectA)
		Epic epicBB = new Epic(name: "Epic BB", description: "Description of Epic BB", project: projectB)
		projectB.addToEpics(epicBA)
		projectB.addToEpics(epicBB)
		
		Role roleA = new Role(name: "Role A", description: "Description of Role A", project: projectA)
		Role roleB = new Role(name: "Role B", description: "Description of Role B", project: projectA)
		Role roleC = new Role(name: "Role C", description: "Description of Role C", project: projectA)
		Role roleD = new Role(name: "Role D", description: "Description of Role D", project: projectA)
		
		UserStory storyAAA = new UserStory(name: "Story AAA", benefit: "Benefit ot Story AAA", goal: "Goal of Story AAA", description: "Description of Story AAA")
		UserStory storyAAB = new UserStory(name: "Story AAB", benefit: "Benefit ot Story AAB", goal: "Goal of Story AAB", description: "Description of Story AAB")
		epicAA.addToUserStories(storyAAA)
		epicAA.addToUserStories(storyAAB)
		roleA.addToUserStories(storyAAA)
		roleA.addToUserStories(storyAAB)
		
		UserStory storyABA = new UserStory(name: "Story ABA", benefit: "Benefit ot Story ABA", goal: "Goal of Story ABA", description: "Description of Story ABA")
		UserStory storyABB = new UserStory(name: "Story ABB", benefit: "Benefit ot Story ABB", goal: "Goal of Story ABB", description: "Description of Story ABB")
		epicAB.addToUserStories(storyABA)
		epicAB.addToUserStories(storyABB)
		roleB.addToUserStories(storyABA)
		roleB.addToUserStories(storyABB)

		UserStory storyBAA = new UserStory(name: "Story BAA", benefit: "Benefit ot Story BAA", goal: "Goal of Story BAA", description: "Description of Story BAA")
		UserStory storyBAB = new UserStory(name: "Story BAB", benefit: "Benefit ot Story BAB", goal: "Goal of Story BAB", description: "Description of Story BAB")
		epicBA.addToUserStories(storyBAA)
		epicBA.addToUserStories(storyBAB)
		roleC.addToUserStories(storyBAA)
		roleC.addToUserStories(storyBAB)

		UserStory storyBBA = new UserStory(name: "Story BBA", benefit: "Benefit ot Story BBA", goal: "Goal of Story BBA", description: "Description of Story BBA")
		UserStory storyBBB = new UserStory(name: "Story BBB", benefit: "Benefit ot Story BBB", goal: "Goal of Story BBB", description: "Description of Story BBB")
		epicBB.addToUserStories(storyBBA)
		epicBB.addToUserStories(storyBBB)
		roleD.addToUserStories(storyBBA)
		roleD.addToUserStories(storyBBB)
		
		RoadMap roadMapAA = new RoadMap(name: "RoadMap AA", description: "Description of RoadMap AA", project: projectA)
		RoadMap roadMapAB = new RoadMap(name: "RoadMap AB", description: "Description of RoadMap AB", project: projectA)		
		RoadMap roadMapBA = new RoadMap(name: "RoadMap BA", description: "Description of RoadMap BA", project: projectB)
		RoadMap roadMapBB = new RoadMap(name: "RoadMap BB", description: "Description of RoadMap BB", project: projectB)
		
		MileStone mileStoneAAA = new MileStone(name: "MileStone AAA", description: "Description of MileStone AAA", oderId: 1)
		MileStone mileStoneAAB = new MileStone(name: "MileStone AAB", description: "Description of MileStone AAB", oderId: 1)
		roadMapAA.addToMileStones(mileStoneAAA)
		roadMapAB.addToMileStones(mileStoneAAB)
		
		MileStone mileStoneABA = new MileStone(name: "MileStone ABA", description: "Description of MileStone ABA", oderId: 1)
		MileStone mileStoneABB = new MileStone(name: "MileStone ABB", description: "Description of MileStone ABB", oderId: 1)
		roadMapBA.addToMileStones(mileStoneABA)
		roadMapBB.addToMileStones(mileStoneABB)
		
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
		
		roleA.save(failOnError: true)
		roleB.save(failOnError: true)
		roleC.save(failOnError: true)
		roleD.save(failOnError: true)
		
		roadMapAA.save(failOnError: true)
		roadMapAB.save(failOnError: true)
		roadMapBA.save(failOnError: true)
		roadMapBB.save(failOnError: true)
		
		mileStoneAAA.save(failOnError: true)
		mileStoneAAB.save(failOnError: true)
		mileStoneABA.save(failOnError: true)
		mileStoneABB.save(failOnError: true)
		

				println "Created the Epic: $epicAA"
	}
}
