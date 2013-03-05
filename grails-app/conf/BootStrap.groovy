import grails.converters.JSON
import org.codehaus.groovy.grails.web.converters.marshaller.json.DomainClassMarshaller 

class BootStrap {

    def init = {servletContext ->		
		/*
            JSON.registerObjectMarshaller(
                new NoClassNameObjectMarshaller(), 1)
            JSON.registerObjectMarshaller(
                new DomainClassMarshaller(true), 2)
        */
        }
	
    def destroy = {
    }
}
