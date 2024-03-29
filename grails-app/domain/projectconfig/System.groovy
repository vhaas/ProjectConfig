package projectconfig

class System {

	Project project

	String name
	String description

	static hasMany = [
		systemChanges: SystemChange
	]

	static constraints = { systemChanges nullable: true }

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
		return "systems"
	}
}