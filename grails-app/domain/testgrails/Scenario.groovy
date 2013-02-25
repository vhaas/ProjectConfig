package testgrails

import org.hibernate.envers.Audited

@Audited
class Scenario {
	
	String name
	String description
	String trigger
	String postConditions	
	
	UseCase useCase
	Iteration iteration
	
	static hasMany = [
		steps: Step,
		interfaceChanges: InterfaceChange
		]

    static constraints = {
    }
}
