package testgrails

import org.hibernate.envers.Audited

@Audited
class Step {
	
	String name
	String description
	Integer orderId
	
	static belongsTo = Scenario
	static hasMany = [scenarios: Scenario]

    static constraints = {
    }
}
