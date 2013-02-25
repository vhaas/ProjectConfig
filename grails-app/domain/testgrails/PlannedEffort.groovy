package testgrails

import org.hibernate.envers.Audited

@Audited
class PlannedEffort {
	
	String effortType
	Integer minEffort
	Integer medEffort
	Integer maxEffort
	Integer risk
	
	EffortEstimate effortEstimate
	
	static hasMany = [
		neededProfiles: Profile,
		plannedEfforts: PlannedEffort,
		moduleChanges: ModuleChange
		]	

    static constraints = {
    }
}
