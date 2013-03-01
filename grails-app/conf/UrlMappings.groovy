class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###UserStory
		"/userStory/$id"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showUserStoryById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/userStories"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showAllUserStories"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Role		
		"/roles"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showAllRoles"]
		}
		
		"/role/$id"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showRoleById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Epic
		"/epics"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showAllEpics"]
		}
		
		"/epicUserStories/$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showUserStoriesByEpicId"]
		}
		
		"/epic/$id"(controller: "EpicRest", parseRequest: true) {
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
