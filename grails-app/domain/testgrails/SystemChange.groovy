package testgrails

class SystemChange {
	
	String adaptionType
	String adaptionAspect
	
	FirstEffortEstimate firstEffortEstimate
	System system
	
	static belongsTo = UserStory
	static hasMany = [
		userStories: UserStory
		]

    static constraints = {
    }
}
