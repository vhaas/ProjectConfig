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
	
	public transformToMap(boolean withOutRootElement = false) {
		def map = [
				id: id,
				name: name,
				description: description,
				project: project ? project.transformToMap(true) : ''
		]
		if (withOutRootElement) {
			return [id: id]
		}
		else {
			map = ["epic": map]
			return map
		}		
	}	
}
