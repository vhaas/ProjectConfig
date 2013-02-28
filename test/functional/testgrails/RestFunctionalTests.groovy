package testgrails

import com.grailsrocks.functionaltest.*

class RestFunctionalTests extends BrowserTestCase {
    
	void testGetRoles() {
		(new Role(name: "Administrator", description: "The most important person in da house")).save()
		(new Role(name: "Standard User", description: "He's ain't no allowed anything")).save()
		assertEquals 2, Role.count()
	  
		get('http://localhost:8080/TestGrails/rolenames') 
		assertStatus 200
	  
		assertContentContains "Administrator"
		assertContentContains "Standard User"
	 }
}
