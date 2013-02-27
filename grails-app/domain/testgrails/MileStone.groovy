package testgrails

class MileStone {
	
	String name
	String description
	Date dueTo
	Integer oderId
	
	RoadMap roadMap

    static constraints = {
		name nullable: false
		description nullable: false
		dueTo nullable: false
    }
}
