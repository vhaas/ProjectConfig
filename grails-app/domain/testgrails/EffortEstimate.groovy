package testgrails

import org.hibernate.envers.Audited

@Audited
class EffortEstimate {
	
	String effortType
	Integer minEffort
	Integer medEffort
	Integer maxEffort
	Integer risk
	
	FirstEffortEstimate firstEffortEstimate
	
	static hasMany = [
		neededProfiles: Profile,
		moduleChanges: ModuleChange,
		plannedEfforts: PlannedEffort
		]

    static constraints = {
    }
}
