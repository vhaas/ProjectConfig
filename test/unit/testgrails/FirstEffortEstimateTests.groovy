package testgrails

import grails.test.mixin.*
import org.junit.*

import projectconfig.FirstEffortEstimate;
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(FirstEffortEstimate)
@Build(FirstEffortEstimate)
class FirstEffortEstimateTests {

    void testCreateDefaultConstructor() {
		FirstEffortEstimate firstEffortEstimate = new FirstEffortEstimate()
		firstEffortEstimate.getProperties().each { property ->
			if (property.key == "id") {
				assert property.key != null
			}
			else {
				assertNull(property.value)
			}			
		}
		def firstEffortEstimateWithValues = FirstEffortEstimate.build()
		assert firstEffortEstimateWithValues.effortType != null
		assert firstEffortEstimateWithValues.minEffort != null
		assert firstEffortEstimateWithValues.medEffort != null
		assert firstEffortEstimateWithValues.maxEffort != null
		assert firstEffortEstimateWithValues.risk != null
	}
}