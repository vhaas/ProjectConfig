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
		
		EffortRole effortRoleA = new EffortRole(name: "EffortRole A", description: "Description of EffortRole A", dailyRate: "100")
		EffortRole effortRoleB = new EffortRole(name: "EffortRole B", description: "Description of EffortRole B", dailyRate: "200")
		EffortRole effortRoleC = new EffortRole(name: "EffortRole C", description: "Description of EffortRole C", dailyRate: "300")
		EffortRole effortRoleD = new EffortRole(name: "EffortRole D", description: "Description of EffortRole D", dailyRate: "400")
		
		FirstEffortEstimate firstEffortEstimateAAAA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate AAAA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		FirstEffortEstimate firstEffortEstimateAAAB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate AAAB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		
		FirstEffortEstimate firstEffortEstimateAABA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate AABA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		FirstEffortEstimate firstEffortEstimateAABB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate AABB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		
		SystemChange systemChangeAAA = new SystemChange(adaptionAspect: "Adaption Aspect AAA", project: projectA, adaptionType: adaptionTypeA, system: systemAA)
		SystemChange systemChangeAAB = new SystemChange(adaptionAspect: "Adaption Aspect AAB", project: projectA, adaptionType: adaptionTypeA, system: systemAA)
		storyAAA.addToSystemChanges(systemChangeAAA)
		storyAAB.addToSystemChanges(systemChangeAAB)
		systemChangeAAA.addToFirstEffortEstimates(firstEffortEstimateAAAA)
		systemChangeAAA.addToFirstEffortEstimates(firstEffortEstimateAAAB)
		systemChangeAAB.addToFirstEffortEstimates(firstEffortEstimateAABA)
		systemChangeAAB.addToFirstEffortEstimates(firstEffortEstimateAABB)
		
		FirstEffortEstimate firstEffortEstimateABAA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate ABAA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		FirstEffortEstimate firstEffortEstimateABAB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate ABAB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		
		FirstEffortEstimate firstEffortEstimateABBA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate ABBA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)
		FirstEffortEstimate firstEffortEstimateABBB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate ABBB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectA)

		SystemChange systemChangeABA = new SystemChange(adaptionAspect: "Adaption Aspect ABA", project: projectA, adaptionType: adaptionTypeB, system: systemAB)
		SystemChange systemChangeABB = new SystemChange(adaptionAspect: "Adaption Aspect ABB", project: projectA, adaptionType: adaptionTypeB, system: systemAB)
		storyABA.addToSystemChanges(systemChangeABA)
		storyABB.addToSystemChanges(systemChangeABB)
		systemChangeABA.addToFirstEffortEstimates(firstEffortEstimateABAA)
		systemChangeABA.addToFirstEffortEstimates(firstEffortEstimateABAB)
		systemChangeABB.addToFirstEffortEstimates(firstEffortEstimateABBA)
		systemChangeABB.addToFirstEffortEstimates(firstEffortEstimateABBB)
		
		FirstEffortEstimate firstEffortEstimateBAAA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BAAA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		FirstEffortEstimate firstEffortEstimateBAAB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BAAB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		
		FirstEffortEstimate firstEffortEstimateBABA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BABA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		FirstEffortEstimate firstEffortEstimateBABB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BABB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)

		SystemChange systemChangeBAA = new SystemChange(adaptionAspect: "Adaption Aspect BAA", project: projectB, adaptionType: adaptionTypeC, system: systemBA)
		SystemChange systemChangeBAB = new SystemChange(adaptionAspect: "Adaption Aspect BAB", project: projectB, adaptionType: adaptionTypeC, system: systemBA)
		storyBAA.addToSystemChanges(systemChangeBAA)
		storyBAB.addToSystemChanges(systemChangeBAB)
		systemChangeBAA.addToFirstEffortEstimates(firstEffortEstimateBAAA)
		systemChangeBAA.addToFirstEffortEstimates(firstEffortEstimateBAAB)
		systemChangeBAB.addToFirstEffortEstimates(firstEffortEstimateBABA)
		systemChangeBAB.addToFirstEffortEstimates(firstEffortEstimateBABB)
		
		FirstEffortEstimate firstEffortEstimateBBAA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BBAA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		FirstEffortEstimate firstEffortEstimateBBAB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BBAB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		
		FirstEffortEstimate firstEffortEstimateBBBA = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BBBA", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)
		FirstEffortEstimate firstEffortEstimateBBBB = new FirstEffortEstimate(effortType: "Effort Type of First Effort Estimate BBBB", minEffort: "1", medEffort: "2", maxEffort: "3", risk: "50", project: projectB)

		SystemChange systemChangeBBA = new SystemChange(adaptionAspect: "Adaption Aspect BBA", project: projectB, adaptionType: adaptionTypeD, system: systemBB)
		SystemChange systemChangeBBB = new SystemChange(adaptionAspect: "Adaption Aspect BBB", project: projectB, adaptionType: adaptionTypeD, system: systemBB)		
		storyBBA.addToSystemChanges(systemChangeBBA)
		storyBBB.addToSystemChanges(systemChangeBBB)
		systemChangeBBA.addToFirstEffortEstimates(firstEffortEstimateBBAA)
		systemChangeBBA.addToFirstEffortEstimates(firstEffortEstimateBBAB)
		systemChangeBBB.addToFirstEffortEstimates(firstEffortEstimateBBBA)
		systemChangeBBB.addToFirstEffortEstimates(firstEffortEstimateBBBB)		
		
		effortRoleA.addToFirstEffortEstimates(firstEffortEstimateAAAA)
		effortRoleB.addToFirstEffortEstimates(firstEffortEstimateAAAB)
		effortRoleC.addToFirstEffortEstimates(firstEffortEstimateAABA)
		effortRoleD.addToFirstEffortEstimates(firstEffortEstimateAABB)
		effortRoleD.addToFirstEffortEstimates(firstEffortEstimateABAA)
		effortRoleC.addToFirstEffortEstimates(firstEffortEstimateABAB)
		effortRoleB.addToFirstEffortEstimates(firstEffortEstimateABBA)
		effortRoleA.addToFirstEffortEstimates(firstEffortEstimateABBB)
		effortRoleA.addToFirstEffortEstimates(firstEffortEstimateBAAA)
		effortRoleB.addToFirstEffortEstimates(firstEffortEstimateBAAB)
		effortRoleC.addToFirstEffortEstimates(firstEffortEstimateBABA)
		effortRoleD.addToFirstEffortEstimates(firstEffortEstimateBABB)
		effortRoleD.addToFirstEffortEstimates(firstEffortEstimateBBAA)
		effortRoleC.addToFirstEffortEstimates(firstEffortEstimateBBAB)
		effortRoleB.addToFirstEffortEstimates(firstEffortEstimateBBBA)
		effortRoleA.addToFirstEffortEstimates(firstEffortEstimateBBBB)
		
		
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
		
		effortRoleA.save(failOnError: true)
		effortRoleB.save(failOnError: true)
		effortRoleC.save(failOnError: true)
		effortRoleD.save(failOnError: true)
		
		systemChangeAAA.save(failOnError: true)
		systemChangeAAB.save(failOnError: true)
		systemChangeABA.save(failOnError: true)
		systemChangeABB.save(failOnError: true)
		systemChangeBAA.save(failOnError: true)
		systemChangeBAB.save(failOnError: true)
		systemChangeBBA.save(failOnError: true)
		systemChangeBBB.save(failOnError: true)
		
		firstEffortEstimateAAAA.save(failOnError: true)
		firstEffortEstimateAAAB.save(failOnError: true)
		firstEffortEstimateAABA.save(failOnError: true)
		firstEffortEstimateAABB.save(failOnError: true)
		firstEffortEstimateABAA.save(failOnError: true)
		firstEffortEstimateABAB.save(failOnError: true)
		firstEffortEstimateABBA.save(failOnError: true)
		firstEffortEstimateABBB.save(failOnError: true)
		firstEffortEstimateBAAA.save(failOnError: true)
		firstEffortEstimateBAAB.save(failOnError: true)
		firstEffortEstimateBABA.save(failOnError: true)
		firstEffortEstimateBABB.save(failOnError: true)
		firstEffortEstimateBBAA.save(failOnError: true)
		firstEffortEstimateBBAB.save(failOnError: true)
		firstEffortEstimateBBBA.save(failOnError: true)
		firstEffortEstimateBBBB.save(failOnError: true)
		
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