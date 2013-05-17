package testgrails

import grails.test.mixin.*
import org.junit.*

import projectconfig.Epic;
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Epic)
@Build(Epic)
class EpicTests {

    void testCreateDefaultConstructor() {
		Epic epic = new Epic()
		epic.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def epicWithValues = Epic.build()
		assert epicWithValues.name != null
		assert epicWithValues.description != null
	}
}