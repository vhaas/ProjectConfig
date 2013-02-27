package testgrails

class Role {
	
	String name
	String description
	
	static hasMany = [
		userStories: UserStory
		]

    static constraints = {
    }
}
