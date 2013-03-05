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
			action = [GET: "showAllUserStories", POST: "save"]
		}
		
		"/rest/user_stories?epic=$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showUserStoriesByEpicId"]
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
		"/rest/epics/$id"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showEpicById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/rest/epics"(controller: "EpicRest", parseRequest: true) {
			action = [GET: "showAllEpics"]
		}		
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Project
		"/rest/projects/$id"(controller: "ProjectRest", parseRequest: true) {
			action = [GET: "showProjectById"]
		}
		
		"/rest/projects"(controller: "ProjectRest", parseRequest: true) {
			action = [GET: "showAllProjects"]
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
