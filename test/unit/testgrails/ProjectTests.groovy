package testgrails

import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Project)
@Build(Project)
class ProjectTests {

    void testCreateDefaultConstructor() {
		Project project = new Project()
		project.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def projectWithValues = Project.build()
		assert projectWithValues.name != null
		assert projectWithValues.description != null
	}
}