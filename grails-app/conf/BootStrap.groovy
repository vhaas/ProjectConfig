import grails.converters.JSON
import org.codehaus.groovy.grails.web.converters.marshaller.json.DomainClassMarshaller 
import testgrails.TestDataController

class BootStrap {

	def testDataGenerationService
	
    def init = {servletContext ->		
		testDataGenerationService.buildTestData()
    }
	
    def destroy = {
    }
}
