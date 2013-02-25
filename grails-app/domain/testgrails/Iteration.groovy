package testgrails

import org.hibernate.envers.Audited

@Audited
class Iteration {
	
	Date date
	String description
	Integer orderId
	
	Release release
	
	static hasMany = [scenarios: Scenario]

    static constraints = {
    }
}
