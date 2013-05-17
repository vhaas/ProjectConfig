package projectconfig

class FirstEffortEstimate {
	
	Project project
	
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
	
	public String getMultipleRoot() {
		return "first_effort_estimates"
	}
}
