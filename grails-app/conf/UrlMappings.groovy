class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{ constraints { // apply constraints here
			} }

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
			action = [GET: "showAllEpics", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###Project
		"/rest/projects/$id"(controller: "ProjectRest", parseRequest: true) {
			action = [GET: "showProjectById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/projects"(controller: "ProjectRest", parseRequest: true) {
			action = [GET: "showAllProjects", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###RoadMap
		"/rest/road_maps/$id"(controller: "RoadMapRest", parseRequest: true) {
			action = [GET: "showRoadMapById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/road_maps"(controller: "RoadMapRest", parseRequest: true) {
			action = [GET: "showAllRoadMaps", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###MileStone
		"/rest/mile_stones/$id"(controller: "MileStoneRest", parseRequest: true) {
			action = [GET: "showMileStoneById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/mile_stones"(controller: "MileStoneRest", parseRequest: true) {
			action = [GET: "showAllMileStones", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###System
		"/rest/systems/$id"(controller: "SystemRest", parseRequest: true) {
			action = [GET: "showSystemById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/systems"(controller: "SystemRest", parseRequest: true) {
			action = [GET: "showAllSystems", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###SystemChange
		"/rest/system_changes/$id"(controller: "SystemChangeRest", parseRequest: true) {
			action = [GET: "showSystemChangeById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/system_changes"(controller: "SystemChangeRest", parseRequest: true) {
			action = [GET: "showAllSystemChanges", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###AdaptionType
		"/rest/adaption_types/$id"(controller: "AdaptionTypeRest", parseRequest: true) {
			action = [GET: "showAdaptionTypeById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/adaption_types"(controller: "AdaptionTypeRest", parseRequest: true) {
			action = [GET: "showAllAdaptionTypes", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###FirstEffortEstimate
		"/rest/first_effort_estimates/$id"(controller: "FirstEffortEstimateRest", parseRequest: true) {
			action = [GET: "showFirstEffortEstimateById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/first_effort_estimates"(controller: "FirstEffortEstimateRest", parseRequest: true) {
			action = [GET: "showAllFirstEffortEstimates", POST: "save"]
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		//###EffortRole
		"/rest/effort_roles/$id"(controller: "EffortRoleRest", parseRequest: true) {
			action = [GET: "showEffortRoleById", PUT: "update", DELETE: "delete", POST: "save"]
		}

		"/rest/effort_roles"(controller: "EffortRoleRest", parseRequest: true) {
			action = [GET: "showAllEffortRole", POST: "save"]
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