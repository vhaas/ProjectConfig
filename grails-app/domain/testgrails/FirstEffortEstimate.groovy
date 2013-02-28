package testgrails

class FirstEffortEstimate {
	
	String effortType
	Integer minEffort
	Integer medEffort
	Integer maxEffort
	Integer risk
	
	static hasMany = [
		systemChanges: SystemChange
		]

    static constraints = {
		systemChanges nullable: true		
    }	
}
