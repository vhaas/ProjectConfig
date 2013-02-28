package testgrails

class RoadMap {
	
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
}
