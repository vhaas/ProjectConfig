package testgrails

class System {
	
	String name
	String description
	
	static hasMany = [
		systemChanges: SystemChange
		]

    static constraints = {
		systemChanges nullable: true		
    }	
}
