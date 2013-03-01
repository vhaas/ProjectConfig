package testgrails

class Epic {
	
	String name
	String description
	
	static hasMany = [
		userStories: UserStory
		]	
	
    static constraints = {
    }	
}
