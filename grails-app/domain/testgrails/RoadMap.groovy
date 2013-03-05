package testgrails

class RoadMap {
	
	Project project
	
	String name
	String description
	
	Configuration configuration
	
	static hasMany = [
		mileStones: MileStone,
		userStories: UserStory
		]

    static constraints = {		
		configuration nullable: true		
    }	
	
	public transformToMap() {
		return [
				id: id,
				name: name,
				description: description,
				configuration_id: configuration ? configuration.id : ''	
		]			
	}
}
