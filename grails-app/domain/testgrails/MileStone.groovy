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
	
	public transformToMap(boolean withOutRootElement = false) {
		def map = [
			id: id,
			name: name,
			description: description
		]
		if (withOutRootElement) {
			return map
		}
		else {
			map = ["mile_stone": map]
			return map
		}		
	}
}
