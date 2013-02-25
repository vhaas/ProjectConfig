package testgrails

import org.hibernate.envers.Audited

@Audited
class Release {
	
	Date date
	String description
	Integer orderId
	
	RoadMap roadMap
	
	static hasMany = [
		useCases: UseCase,
		iterations: Iteration		
		]

    static constraints = {
    }
}
