package testgrails

import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(UserStory)
@Build(UserStory)
class UserStoryTests {	
	
	void testCreateDefaultConstructor() {
		UserStory userStory = new UserStory()
		userStory.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def userStoryWithValues = UserStory.build()
		assert userStoryWithValues.name != null
		assert userStoryWithValues.description != null
		assert userStoryWithValues.goal != null
		assert userStoryWithValues.benefit != null
	}
}