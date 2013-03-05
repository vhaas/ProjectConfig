package testgrails

class MileStone {
	
	Project project
	
	String name
	String description
	Date dueTo
	Integer oderId
	
	RoadMap roadMap

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
			road_map_id: roadMap ? roadMap.id : ''
		]				
	}
}
