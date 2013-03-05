package testgrails

class Epic {
	
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
				description: description,
				project_id: project ? project.id : ''
		]			
	}	
}
