class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###UserStory
		"/rest/user_stories/$id"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showUserStoryById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/user_stories"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showAllUserStories", POST: "save"]
		}		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Role	
		"/rest/roles/$id"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showRoleById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/roles"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showAllRoles", POST: "save"]
		}		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Epic
		"/rest/epics/$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showEpicById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/epics"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showAllEpics"]
		}		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Project
		"/rest/projects/$id"(controller: "ProjectRest", parseRequest: true) {
			action = [GET: "showProjectById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/projects"(controller: "ProjectRest", parseRequest: true) {
			action = [GET: "showAllProjects"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###RoadMap
		"/rest/road_maps/$id"(controller: "RoadMapRest", parseRequest: true) {
			action = [GET: "showRoadMapById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/road_maps"(controller: "RoadMapRest", parseRequest: true) {
			action = [GET: "showAllRoadMaps"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###MileStone
		"/rest/mile_stones/$id"(controller: "MileStoneRest", parseRequest: true) {
			action = [GET: "showMileStoneById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/mile_stones"(controller: "MileStoneRest", parseRequest: true) {
			action = [GET: "showAllMileStones"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Helper
		"/createTestData"(controller: "TestData", parseRequest: true) {
			action = [GET: "testBuildAllDomains"]
		}		

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
