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
	
	public transformToMap(boolean withOutRootElement = false) {
		def map = [
				id: id,
				name: name,
				description: description	
		]
		if (withOutRootElement) {
			return [id: id]
		}
		else {
			map = ["role": map]
			return map
		}		
	}	
}
