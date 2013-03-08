package testgrails

import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RoadMap)
@Build(RoadMap)
class RoadMapTests {

    void testCreateDefaultConstructor() {
		RoadMap roadMap = new RoadMap()
		roadMap.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def roadMapWithValues = RoadMap.build()
		assert roadMapWithValues.name != null
		assert roadMapWithValues.description != null		
	}
}