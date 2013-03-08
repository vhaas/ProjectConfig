package testgrails

import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(System)
@Build(System)
class SystemTests {

    void testCreateDefaultConstructor() {
		System system = new System()
		system.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def systemWithValues = System.build()
		assert systemWithValues.name != null
		assert systemWithValues.description != null
	}
}
