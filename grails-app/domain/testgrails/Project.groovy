package testgrails

class Project {
	
	String name
	String description
	
	static hasMany = [
		configurations: Configuration,
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
	
	public transformToMap(boolean withOutRootElement = false) {
		def map = [
			id: id,
			name: name,
			description: description
		]
		if (withOutRootElement) {
			return map
		}
		else {
			map = ["project": map]
			return map
		}				
	}	
}


