import grails.converters.JSON

 

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
