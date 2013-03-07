import grails.converters.JSON
import org.codehaus.groovy.grails.web.converters.marshaller.json.DomainClassMarshaller 
import testgrails.*

class BootStrap {

	def testDataGenerationService
	
    def init = {servletContext ->
		
		testDataGenerationService.buildCustomizedTestData()
	}
	
    def destroy = {
    }
}
