package projectconfig

class Epic {

	Project project

	String name
	String description

	static hasMany = [
		userStories: UserStory
	]

	static constraints = {
		name nullable: true
		description nullable: true
	}

	public transformToMap() {
		return [
			id: id,
			name: name,
			description: description,
			project_id: project ? project.id : '',
			user_story_ids: userStories.collect{it.id}
		]
	}

	public String getMultipleRoot() {
		return "epics"
	}
}