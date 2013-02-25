package testgrails

import org.hibernate.envers.Audited

@Audited
class InterfaceChange {
	
	String name
	String adaptionType
	String adaptionAspect
	
	PlannedEffort plannedEffort
	ModuleChange moduleChange
	
	static belongsTo = Scenario
	static hasMany = [scenarios: Scenario]	

    static constraints = {
    }
}
