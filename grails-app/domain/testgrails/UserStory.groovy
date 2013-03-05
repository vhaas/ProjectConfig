package testgrails

class UserStory {
	
	Project project
	
	String name
	String description
	String goal
	String benefit
	
	RoadMap roadMap
	Epic epic
	Role role
	
	static hasMany = [
		systemChanges: SystemChange,
		requiredUserStories: UserStory		
		]	

    static constraints = {		
		roadMap nullable: true
		epic nullable: true
		role nullable: true
    }	
	
	public transformToMap(boolean withOutRootElement = false) {
		def map = [
				id: id,
				name: name,
				description: description,
				goal: goal,
				benefit: benefit,
				roadMap: roadMap ? roadMap.transformToMap(true) : '',
				epic: epic ? epic.transformToMap(true) : '',
				role: role ? role.transformToMap(true) : ''				
		]
		if (withOutRootElement) {
			return map
		}
		else {
			map = ["user_story": map]
			return map
		}		
	}	
}
