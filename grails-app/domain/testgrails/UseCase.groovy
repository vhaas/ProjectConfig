package testgrails

import org.hibernate.envers.Audited

@Audited
class UseCase {
	
	String name
	String description
	String goal
	
	Release release
	
	static belongsTo = UserStory
	static hasMany = [
		userStories: UserStory,
		actors: Actor,
		preConditions: PreConditions,
		includedUseCases: UseCase,
		extendUseCases: UseCase,
		requiredUseCases: UseCase,
		scenarios: Scenario
		]	

    static constraints = {
    }
}
