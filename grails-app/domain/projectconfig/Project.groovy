package projectconfig

class Project {

	String name
	String description

	static hasMany = [		
		epics: Epic,
		firstEffortEstimates: FirstEffortEstimate,
		mileStones: MileStone,
		roadMaps: RoadMap,
		roles: Role,
		systems: System,
		systemChanges: SystemChange,
		userStories: UserStory
	]

	static constraints = {
	}

	public transformToMap() {
		return [
			id: id,
			name: name,
			description: description,
			epic_ids: epics.collect{it.id},			
			road_map_ids: roadMaps.collect{it.id},
			system_ids: systems.collect{it.id}
		]
	}

	public String getMultipleRoot() {
		return "projects"
	}
}