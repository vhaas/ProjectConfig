package testgrails

import org.hibernate.envers.Audited

@Audited
class SystemChange {
	
	String name
	String adaptionType
	String adaptionAspect
	
	FirstEffortEstimate firstEffortEstimate
	
	static belongsTo = UserStory
	static hasMany = [
		moduleChanges: ModuleChange,
		userStories: UserStory
		]	

    static constraints = {
    }
}
