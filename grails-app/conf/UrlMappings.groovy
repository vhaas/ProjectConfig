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
		"/rest/roles"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showAllRoles"]
		}
		
		"/rest/role/$id"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showRoleById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Epic
		"/rest/epics"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showAllEpics"]
		}
		
		"/rest/epicUserStories/$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showUserStoriesByEpicId"]
		}
		
		"/rest/epic/$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showEpicById", PUT: "update", DELETE: "delete", POST: "save"]
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
