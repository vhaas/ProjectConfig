package testgrails

import grails.test.mixin.*
import org.junit.*

import projectconfig.SystemChange;
import grails.buildtestdata.mixin.Build


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(SystemChange)
@Build(SystemChange)
class SystemChangeTests {
	
	void testCreateDefaultConstructor() {
		SystemChange systemChange = new SystemChange()
		systemChange.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}
		}
		def systemChangeWithValues = SystemChange.build()
		assert systemChangeWithValues.adaptionType != null
		assert systemChangeWithValues.adaptionAspect != null
	}
}