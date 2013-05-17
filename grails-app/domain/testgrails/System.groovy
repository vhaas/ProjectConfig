package testgrails

class System {
	
	Project project
	
	String name
	String description
	
	static hasMany = [
		systemChanges: SystemChange
		]

    static constraints = {
		systemChanges nullable: true		
    }
	
	public String getMultipleRoot() {
		return "systems"
	}
}
