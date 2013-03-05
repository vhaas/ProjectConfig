package testgrails

class Role {
	
	Project project
	
	String name
	String description
	
	static hasMany = [
		userStories: UserStory
		]

    static constraints = {
    }	
	
	public transformToMap() {
		return [
				id: id,
				name: name,
				description: description	
		]				
	}	
}
