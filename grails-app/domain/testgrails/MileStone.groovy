package testgrails

class MileStone {
	
	Project project
	
	String name
	String description
	Date dueTo
	Integer oderId
	
	RoadMap roadMap
	
	static hasMany = [
		userStories: UserStory
		]

    static constraints = {
		name nullable: true
		description nullable: true
		dueTo nullable: true		
    }
	
	public transformToMap() {
		return [
				id: id,
				name: name,
				description: description,
				road_map_id: roadMap ? roadMap.id : '',
				user_story_ids: userStories.collect{it.id},
				oderId: orderId
		]
	}
	
	public String getMultipleRoot() {
		return "mile_stones"
	}
}
