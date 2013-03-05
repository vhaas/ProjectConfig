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
	
	public transformToMap(boolean withOutRootElement = false) {
		def map = [
				id: id,
				name: name,
				description: description,
				configuration: configuration ? configuration.transformToMap() : ''	
		]
		if (withOutRootElement) {
			return [id: id]
		}
		else {
			map = ["road_map": map]
			return map
		}		
	}
}
