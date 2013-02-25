package testgrails

import org.hibernate.envers.Audited

@Audited
class Epic {
	
	String name
	String description
	
	static hasMany = [userStories: UserStory]

    static constraints = {
    }
}
