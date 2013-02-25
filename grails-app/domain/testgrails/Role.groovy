package testgrails

import org.hibernate.envers.Audited

@Audited
class Role {
	
	String name
	String description
	
	static hasMany = [userStories: UserStory]

    static constraints = {
    }
}
