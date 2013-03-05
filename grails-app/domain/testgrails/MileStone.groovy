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
}
