package projectconfig

class Configuration {
	
	Project project

	String name
	String description
	
    static constraints = {
    }
	
	public transformToMap() {
		return [
				id: id,
				name: name,
				description: description	
		]				
	}
	
	public String getMultipleRoot() {
		return "configurations"
	}
}
