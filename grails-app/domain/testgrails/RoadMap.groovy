package testgrails

import org.hibernate.envers.Audited

@Audited
class RoadMap {
	
	Date date
	String description
	Integer orderId
	
	static hasMany = [
		userStories: UserStory,
		releases: Release
		]

    static constraints = {
    }
}
