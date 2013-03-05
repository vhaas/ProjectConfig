class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###UserStory
		"/rest/userStory/$id"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showUserStoryById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/user_stories"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showAllUserStories"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Role	
		"/rest/role/$id"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showRoleById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/roles"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showAllRoles"]
		}		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Epic
		"/rest/epic/$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showEpicById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/epics"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showAllEpics"]
		}
		
		"/rest/epicUserStories/$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showUserStoriesByEpicId"]
		}		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Project
		"/rest/project/$id"(controller: "ProjectRest", parseRequest: true) {
			action = [GET: "showProjectById"]
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Helper
		"/createTestData"(controller: "TestData", parseRequest: true) {
			action = [GET: "testBuildAllDomains"]
		}
		
		"/createManualTestData"(controller: "TestData", parseRequest: true) {
			action = [GET: "buildManualDomains"]
		}		
		

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
