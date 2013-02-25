package testgrails

import org.hibernate.envers.Audited

@Audited
class FirstEffortEstimate {
	
	String effortType
	Integer minEffort
	Integer medEffort
	Integer maxEffort
	Integer risk
	
	static hasMany = [
		systemChanges: SystemChange,
		effortEstimates: EffortEstimate
		]	

    static constraints = {
    }
}
