package testgrails

import java.util.Date;
import grails.test.mixin.*
import org.junit.*
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(MileStone)
@Build(MileStone)
class MileStoneTests {

    void testCreateDefaultConstructor() {
		MileStone mileStone = new MileStone()
		mileStone.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def mileStoneWithValues = MileStone.build()
		mileStoneWithValues != null
	}
}