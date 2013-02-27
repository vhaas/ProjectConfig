class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/userStory/$id"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showUserStoryById", PUT: "update", DELETE: "delete", POST: "save"]
		}
		
		"/userStories"(controller: "UserStoryRest", parseRequest: true) {
			action = [GET: "showAllUserStories"]
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
