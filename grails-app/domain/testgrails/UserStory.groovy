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
	
	public transformToMap() {
		return [
				id: id,
				name: name,
				description: description,
				goal: goal,
				benefit: benefit,
				roadMap: roadMap ? roadMap.transformToMap() : '',
				epic: epic ? epic.transformToMap() : '',
				role: role ? role.transformToMap() : ''				
		]
	}	
}
