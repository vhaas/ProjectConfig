package projectconfig

class AdaptionType {

	Project project
	
	String name
	String description
	
	static hasMany = [
		systemChanges: SystemChange
	]
	
    static constraints = {
    }
	
	public transformToMap() {
		return [
			id: id,
			name: name,
			description: description,
			project_id: project ? project.id : '',			
			system_change_ids: systemChanges.collect{it.id}
		]
	}

	public String getMultipleRoot() {
		return "adaption_types"
	}
}
