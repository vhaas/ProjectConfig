class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/userstory/$id"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showUserStoryById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/userstories"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showAllUserStories"]
		}
		
		"/rolenames"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showAllRoleNames"]
		}
		
		"/roles"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showAllRoles"]
		}
		
		"/role/$id"(controller: "RoleRest", parseRequest: true) {
			action = [GET: "showRoleById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/epicnames"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showAllEpicNames"]
		}
		
		"/epics"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showAllEpics"]
		}
		
		"/createtestdata"(controller: "TestData", parseRequest: true) {
			action = [GET: "testBuildAllDomains"]
		}
		
		"/createmanualtestdata"(controller: "TestData", parseRequest: true) {
			action = [GET: "buildManualDomains"]
		}		
		

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
