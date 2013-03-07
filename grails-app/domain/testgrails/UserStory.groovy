package testgrails

class UserStory {
	
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
				road_map_id: roadMap ? roadMap.id : '',
				epic_id: epic ? epic.id : '',
				role_id: role ? role.id : ''				
		]			
	}	
}
