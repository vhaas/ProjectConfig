package projectconfig

import projectconfig.Epic
import projectconfig.Project
import projectconfig.Role
import projectconfig.UserStory

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
		Role roleC = new Role(name: "Role C", description: "Description of Role C", project: projectB)
		Role roleD = new Role(name: "Role D", description: "Description of Role D", project: projectB)

		UserStory storyAAAA = new UserStory(name: "Story AAAA", benefit: "Benefit ot Story AAAA", goal: "Goal of Story AAAA", description: "Description of Story AAAA", project: projectA)
		UserStory storyAAA = new UserStory(name: "Story AAA", benefit: "Benefit ot Story AAA", goal: "Goal of Story AAA", description: "Description of Story AAA", project: projectA)
		UserStory storyAAB = new UserStory(name: "Story AAB", benefit: "Benefit ot Story AAB", goal: "Goal of Story AAB", description: "Description of Story AAB", project: projectA)
		epicAA.addToUserStories(storyAAAA)
		epicAA.addToUserStories(storyAAA)
		epicAA.addToUserStories(storyAAB)
		roleA.addToUserStories(storyAAAA)
		roleA.addToUserStories(storyAAA)
		roleA.addToUserStories(storyAAB)

		UserStory storyABA = new UserStory(name: "Story ABA", benefit: "Benefit ot Story ABA", goal: "Goal of Story ABA", description: "Description of Story ABA", project: projectA)
		UserStory storyABB = new UserStory(name: "Story ABB", benefit: "Benefit ot Story ABB", goal: "Goal of Story ABB", description: "Description of Story ABB", project: projectA)
		epicAB.addToUserStories(storyABA)
		epicAB.addToUserStories(storyABB)
		roleB.addToUserStories(storyABA)
		roleB.addToUserStories(storyABB)

		UserStory storyBAA = new UserStory(name: "Story BAA", benefit: "Benefit ot Story BAA", goal: "Goal of Story BAA", description: "Description of Story BAA", project: projectB)
		UserStory storyBAB = new UserStory(name: "Story BAB", benefit: "Benefit ot Story BAB", goal: "Goal of Story BAB", description: "Description of Story BAB", project: projectB)
		epicBA.addToUserStories(storyBAA)
		epicBA.addToUserStories(storyBAB)
		roleC.addToUserStories(storyBAA)
		roleC.addToUserStories(storyBAB)

		UserStory storyBBA = new UserStory(name: "Story BBA", benefit: "Benefit ot Story BBA", goal: "Goal of Story BBA", description: "Description of Story BBA", project: projectB)
		UserStory storyBBB = new UserStory(name: "Story BBB", benefit: "Benefit ot Story BBB", goal: "Goal of Story BBB", description: "Description of Story BBB", project: projectB)
		epicBB.addToUserStories(storyBBA)
		epicBB.addToUserStories(storyBBB)
		roleD.addToUserStories(storyBBA)
		roleD.addToUserStories(storyBBB)

		RoadMap roadMapAA = new RoadMap(name: "RoadMap AA", description: "Description of RoadMap AA", project: projectA)
		RoadMap roadMapAB = new RoadMap(name: "RoadMap AB", description: "Description of RoadMap AB", project: projectA)
		RoadMap roadMapBA = new RoadMap(name: "RoadMap BA", description: "Description of RoadMap BA", project: projectB)
		RoadMap roadMapBB = new RoadMap(name: "RoadMap BB", description: "Description of RoadMap BB", project: projectB)

		MileStone mileStoneAAA = new MileStone(name: "MileStone AAA", description: "Description of MileStone AAA", orderId: 1, project: projectA)
		MileStone mileStoneAAB = new MileStone(name: "MileStone AAB", description: "Description of MileStone AAB", orderId: 1, project: projectA)
		roadMapAA.addToMileStones(mileStoneAAA)
		roadMapAB.addToMileStones(mileStoneAAB)
		mileStoneAAA.addToUserStories(storyAAA)
		mileStoneAAA.addToUserStories(storyAAB)
		mileStoneAAB.addToUserStories(storyABA)
		mileStoneAAB.addToUserStories(storyABB)

		MileStone mileStoneABA = new MileStone(name: "MileStone ABA", description: "Description of MileStone ABA", orderId: 1, project: projectB)
		MileStone mileStoneABB = new MileStone(name: "MileStone ABB", description: "Description of MileStone ABB", orderId: 1, project: projectB)
		roadMapBA.addToMileStones(mileStoneABA)
		roadMapBB.addToMileStones(mileStoneABB)
		mileStoneABA.addToUserStories(storyBAA)
		mileStoneABA.addToUserStories(storyBAB)
		mileStoneABB.addToUserStories(storyBBA)
		mileStoneABB.addToUserStories(storyBBB)
		
		System systemAA = new System(name: "System AA", description: "Description of System AA", project: projectA)
		System systemAB = new System(name: "System AB", description: "Description of System AB", project: projectA)
		System systemBA = new System(name: "System BA", description: "Description of System BA", project: projectB)
		System systemBB = new System(name: "System BB", description: "Description of System BB", project: projectB)
		
		AdaptionType adaptionTypeA = new AdaptionType(name: "AdaptionType A", description: "Description of AdaptionType A", project: projectA)
		AdaptionType adaptionTypeB = new AdaptionType(name: "AdaptionType B", description: "Description of AdaptionType B", project: projectA)
		AdaptionType adaptionTypeC = new AdaptionType(name: "AdaptionType C", description: "Description of AdaptionType C", project: projectB)
		AdaptionType adaptionTypeD = new AdaptionType(name: "AdaptionType D", description: "Description of AdaptionType D", project: projectB)
		
		FirstEffortEstimate firstEffortEstimateAAA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate AAA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		FirstEffortEstimate firstEffortEstimateAAB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate AAB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)		
		
		SystemChange systemChangeAAA = new SystemChange(adaptionAspect: "Adaption Aspect AAA", project: projectA, adaptionType: adaptionTypeA, firstEffortEstimate: firstEffortEstimateAAA, system: systemAA)
		SystemChange systemChangeAAB = new SystemChange(adaptionAspect: "Adaption Aspect AAB", project: projectA, adaptionType: adaptionTypeA, firstEffortEstimate: firstEffortEstimateAAB, system: systemAA)
		storyAAA.addToSystemChanges(systemChangeAAA)
		storyAAB.addToSystemChanges(systemChangeAAB)
		
		FirstEffortEstimate firstEffortEstimateABA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate ABA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		FirstEffortEstimate firstEffortEstimateABB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate ABB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)

		SystemChange systemChangeABA = new SystemChange(adaptionAspect: "Adaption Aspect ABA", project: projectA, adaptionType: adaptionTypeB, firstEffortEstimate: firstEffortEstimateABA, system: systemAB)
		SystemChange systemChangeABB = new SystemChange(adaptionAspect: "Adaption Aspect ABB", project: projectA, adaptionType: adaptionTypeB, firstEffortEstimate: firstEffortEstimateABB, system: systemAB)
		storyABA.addToSystemChanges(systemChangeABA)
		storyABB.addToSystemChanges(systemChangeABB)
		
		FirstEffortEstimate firstEffortEstimateBAA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BAA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		FirstEffortEstimate firstEffortEstimateBAB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BAB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)

		SystemChange systemChangeBAA = new SystemChange(adaptionAspect: "Adaption Aspect BAA", project: projectB, adaptionType: adaptionTypeC, firstEffortEstimate: firstEffortEstimateBAA, system: systemBA)
		SystemChange systemChangeBAB = new SystemChange(adaptionAspect: "Adaption Aspect BAB", project: projectB, adaptionType: adaptionTypeC, firstEffortEstimate: firstEffortEstimateBAB, system: systemBA)
		storyBAA.addToSystemChanges(systemChangeBAA)
		storyBAB.addToSystemChanges(systemChangeBAB)
		
		FirstEffortEstimate firstEffortEstimateBBA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BBA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		FirstEffortEstimate firstEffortEstimateBBB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BBB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)

		SystemChange systemChangeBBA = new SystemChange(adaptionAspect: "Adaption Aspect BBA", project: projectB, adaptionType: adaptionTypeD, firstEffortEstimate: firstEffortEstimateBBA, system: systemBB)
		SystemChange systemChangeBBB = new SystemChange(adaptionAspect: "Adaption Aspect BBB", project: projectB, adaptionType: adaptionTypeD, firstEffortEstimate: firstEffortEstimateBBB, system: systemBB)		
		storyBBA.addToSystemChanges(systemChangeBBA)
		storyBBB.addToSystemChanges(systemChangeBBB)
		
		projectA.save(failOnError: true)
		projectB.save(failOnError: true)
		
		storyAAA.save(failOnError: true)
		storyAAB.save(failOnError: true)
		storyABA.save(failOnError: true)
		storyABB.save(failOnError: true)
		storyBAA.save(failOnError: true)
		storyBAB.save(failOnError: true)
		storyBBA.save(failOnError: true)
		storyBBB.save(failOnError: true)		

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
		
		systemAA.save(failOnError: true)
		systemAB.save(failOnError: true)
		systemBA.save(failOnError: true)
		systemBB.save(failOnError: true)
		
		adaptionTypeA.save(failOnError: true)
		adaptionTypeB.save(failOnError: true)
		adaptionTypeC.save(failOnError: true)
		adaptionTypeD.save(failOnError: true)
		
		firstEffortEstimateAAA.save(failOnError: true)
		firstEffortEstimateAAB.save(failOnError: true)
		firstEffortEstimateABA.save(failOnError: true)
		firstEffortEstimateABB.save(failOnError: true)
		firstEffortEstimateBAA.save(failOnError: true)
		firstEffortEstimateBAB.save(failOnError: true)
		firstEffortEstimateBBA.save(failOnError: true)
		firstEffortEstimateBBB.save(failOnError: true)
		
		systemChangeAAA.save(failOnError: true)
		systemChangeAAB.save(failOnError: true)
		systemChangeABA.save(failOnError: true)
		systemChangeABB.save(failOnError: true)
		systemChangeBAA.save(failOnError: true)
		systemChangeBAB.save(failOnError: true)
		systemChangeBBA.save(failOnError: true)
		systemChangeBBB.save(failOnError: true)
		
		buildAFewProjects()
	}
	
	def buildAFewProjects() {
		String desc = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr," + 
						"sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat," + 
						"sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. ";						
		for (int i = 0; i < 10; i++) {
			Project projectA = new Project(name: "Project " + i, description: desc + i)
			projectA.save(failOnError: true)
		}
	}
}