package projectconfig

class SystemChange {

	Project project
	
	String adaptionAspect

	FirstEffortEstimate firstEffortEstimate
	System system
	AdaptionType adaptionType

	static belongsTo = UserStory
	static hasMany = [
		userStories: UserStory
	]

	static constraints = {
		firstEffortEstimate nullable: true
		system nullable: true
		adaptionType nullable: true
	}

	public transformToMap() {
		return [
			id: id,
			adaption_aspect: adaptionAspect,
			project_id: project ? project.id : '',
			system_id: system ? system.id : '',
			adaption_type_id: adaptionType ? adaptionType.id : '',
			first_effort_estimate_id: firstEffortEstimate ? firstEffortEstimate.id : '',			
			user_story_ids: userStories.collect{it.id}
		]
	}

	public String getMultipleRoot() {
		return "system_changes"
	}
}