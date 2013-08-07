package projectconfig

class EffortRole {
		
	String name
	String description
	String dailyRate
	
	static hasMany = [
		firstEffortEstimates: FirstEffortEstimate
	]

    static constraints = {
    }
	
	public transformToMap() {
		return [
			id: id,
			name: name,
			description: description,			
			first_effort_estimate_ids: firstEffortEstimates.collect{it.id}
		]
	}

	public String getMultipleRoot() {
		return "effort_roles"
	}
}
