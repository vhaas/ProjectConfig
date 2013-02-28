package testgrails

import grails.converters.JSON

class TestDataController {

    def grailsApplication // get grails app injected into test
    
    def testBuildAllDomains() {
        def successful = true
        grailsApplication.domainClasses.each { domainClass ->
            println "Test of ${domainClass.name}.build()"
            try {				
                def domainObject = domainClass.clazz.build()
//	            assertNotNull domainObject."${domainClass.identifier.name}"
                println "********** SUCCESSFUL BUILD OF $domainClass"
				domainObject.save(flush: true)
            } catch (Exception e) {
                println "********** FAILED BUILD OF $domainClass"
                successful = false
            }
        }
        if (successful) {
			render "this worked out"
		}
		else {
			render "didnt what it was told..."
		}
    }
	
	def buildManualDomains() {
		def successful = true
		for (i in 0..9) {
			try {
//				Epic.build().save(flush: true)
//				Role.build().save(flush: true)
//				Configuration.build().save(flush: true)
				MileStone.build().save(flush: true)
//				System.build().save(flush: true)
			} catch (Exception e) {
				println "********** FAILED BUILD"
				successful = false
			}
		}
		if (successful) {
			render "this worked out"
		}
		else {
			render "didnt what it was told..."
		}
	}	
}
