package testgrails

import static org.junit.Assert.*
import org.junit.*

class TestDataTests {

    def grailsApplication // get grails app injected into test
    
    void testBuildAllDomains() {
        def successful = true
        grailsApplication.domainClasses.each { domainClass ->
            println "Test of ${domainClass.name}.build()"
            try {
				for (i in 0..9) {
	                def domainObject = domainClass.clazz.build()
	                assertNotNull domainObject."${domainClass.identifier.name}"
	                println "********** SUCCESSFUL BUILD OF $domainClass"
				}
            } catch (Exception e) {
                println "********** FAILED BUILD OF $domainClass"
                successful = false
            }
        }
        assert successful
    }
}
