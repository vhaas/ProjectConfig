package projectconfig

class MileStone {

	Project project

	String name
	String description
	Date dueTo
	Integer orderId

	RoadMap roadMap

	static hasMany = [
		userStories: UserStory
	]

	static constraints = {
		name nullable: true
		description nullable: true
		dueTo nullable: true
		roadMap nullable: true
	}

	public transformToMap() {
		return [
			id: id,
			name: name,
			description: description,
			road_map_id: roadMap ? roadMap.id : '',
			project_id: project ? project.id : '',
			user_story_ids: userStories.collect{it.id},
			order_id: orderId
		]
	}

	public String getMultipleRoot() {
		return "mile_stones"
	}
}