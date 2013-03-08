package testgrails

import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Role)
@Build(Role)
class RoleTests {

    void testCreateDefaultConstructor() {
		Role role = new Role()
		role.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def roleWithValues = Role.build()
		assert roleWithValues.name != null
		assert roleWithValues.description != null		
	}
}