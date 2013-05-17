package projectconfig

class SystemChange {

	Project project

	String adaptionType
	String adaptionAspect

	FirstEffortEstimate firstEffortEstimate
	System system

	static belongsTo = UserStory
	static hasMany = [
		userStories: UserStory
	]

	static constraints = {
		firstEffortEstimate nullable: true
		system nullable: true
	}

	public String getMultipleRoot() {
		return "system_changes"
	}
}