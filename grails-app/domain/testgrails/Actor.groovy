package testgrails

import org.hibernate.envers.Audited

@Audited
class Actor {
	
	String name
	String description
	
	static belongsTo = UseCase
	static hasMany = [useCases: UseCase]

    static constraints = {
    }
}
