package testgrails

class Configuration {
	
	Project project

	String name
	String description
	
    static constraints = {
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
			map = ["configuration": map]
			return map
		}		
	}
}
