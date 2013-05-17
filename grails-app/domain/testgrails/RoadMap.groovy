package testgrails

class RoadMap {
	
	Project project
	
	String name
	String description
	
	Configuration configuration
	
	static hasMany = [
		mileStones: MileStone		
		]

    static constraints = {		
		configuration nullable: true		
    }	
	
	public transformToMap() {
		return [
				id: id,
				name: name,
				description: description,
				mile_stone_ids: mileStones.collect{it.id}
		]			
	}
	
	public String getMultipleRoot() {
		return "road_maps"
	}
}
