package testgrails

import com.grailsrocks.functionaltest.*
import org.junit.*

class RestFunctionalTests extends BrowserTestCase {
	
	static def url = "http://localhost:8080/TestGrails"
	
	def epic
	def role
	def project
	def userStory
	
	@Before
	void fillTestData() {
		epic = [
			name: "epicName",
			description: "epicDescription"
			]
		epic = ["epic": epic]
		
		role = [
			name: "roleName",
			description: "roleDescription"
			]
		role = ["role": role]
		
		project = [
			name: "projectName",
			description: "projectDescription"
			]
		project = ["project": project]
		
		userStory = [
			name: "userStoryName",
			description: "userStoryDescription",
			goal: "userStoryGoal",
			benefit: "userStoryBenefit",
			road_map_id: "",
			epic_id: "",
			role_id: ""
			]
		userStory = ["user_story": userStory]
	}	
	
	void testPost() {
		this.properties.each { property ->
			if (property.key != "url") {
				def specificUrl = '${url}' + '/${property.value}' + 's'
				println("The POST was send to: " + specificUrl)
				post(specificUrl) {
					body {
						property.value
					}
				}
				assert status 200
			}
		}
	}
}
