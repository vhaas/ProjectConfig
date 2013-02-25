package testgrails

import org.hibernate.envers.Audited

@Audited
class UserStory {
	
	String name
	String goal
	String benefit
	
	Role role
	RoadMap roadMap
	
	static belongsTo = Epic
	static hasMany = [
		epics: Epic,
		systemChanges: SystemChange,
		useCases: UseCase
		]	

    static constraints = {
    }
}
