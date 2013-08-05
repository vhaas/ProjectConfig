package projectconfig

class FirstEffortEstimate {

	Project project

	String effortType
	String minEffort
	String medEffort
	String maxEffort
	String risk
	SystemChange systemChange

	static hasMany = [		
	]

	static constraints = { 
		systemChange nullable: true
	}

	public transformToMap() {
		return [
			id: id,
			effort_type: effortType,
			min_effort: minEffort,
			med_effort: medEffort,
			max_effort: maxEffort,
			risk: risk,
			project_id: project ? project.id : '',
			system_change_id: systemChange ? systemChange.id : ''
		]
	}	
	
	public String getMultipleRoot() {
		return "first_effort_estimates"
	}
}