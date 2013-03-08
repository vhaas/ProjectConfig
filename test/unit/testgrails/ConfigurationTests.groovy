package testgrails

import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Configuration)
@Build(Configuration)
class ConfigurationTests {

    void testCreateDefaultConstructor() {
		Configuration configuration = new Configuration()
		configuration.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def configurationWithValues = Configuration.build()
		assert configurationWithValues.name != null
		assert configurationWithValues.description != null		
	}
}