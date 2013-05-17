package projectconfig

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class RestControllerAssistant {

	static String rootForType(Class clazz) {
		String name = clazz.getName().tokenize('.')[-1]
		return name.getAt(0).toLowerCase() + name[1..-1]
	}

	static JSON renderMultiple(Class clazz, Object it) {
		if(it == null) {
			it = []
		}
		String root = it.getMultipleRoot()
		Map result = [:]
		result [root] = it
		return (result as JSON)
	}

	static JSON renderMultiple_alternative(Class clazz, List objList) {
		List<Map> returnMap = new ArrayList<Map>()
		objList.each {
			def map = it.transformToMap()
			returnMap.add(map)
		}
		def returnedObjects = [(objList.get(0).getMultipleRoot()): returnMap]
		return returnedObjects
	}


	static JSON renderSingle(Class clazz, Object it) {
		String root = rootForType(clazz)
		Map result = [:]
		result [root] = it.transformToMap()
		return (result as JSON)
	}

	static Map extractSingle(Class domain, JSONObject params) {
		return extractMultiple(domain, params)
	}

	static JSONObject extractMultiple(Class domain, JSONObject params) {
		String root = rootForType(domain)
		return params[root]
	}
}
