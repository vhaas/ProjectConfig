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
    }
	
	static namedQueries = {
		
	}
}
