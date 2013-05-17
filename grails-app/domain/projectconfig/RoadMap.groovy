package projectconfig

class RoadMap {

	Project project

	String name
	String description

	Configuration configuration

	static hasMany = [
		mileStones: MileStone
	]

	static constraints = { configuration nullable: true }

	public transformToMap() {
		return [
			id: id,
			name: name,
			description: description,
			project_id: project ? project.id : '',
			mile_stone_ids: mileStones.collect{it.id}
		]
	}

	public String getMultipleRoot() {
		return "road_maps"
	}
}