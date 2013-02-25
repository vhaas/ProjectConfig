package testgrails

import org.hibernate.envers.Audited

@Audited
class Profile {
	
	String name
	String description
	
	static belongsTo = EffortEstimate
	static hasMany = [
		projectStaff: ProjectStaff,
		effortEstimations: EffortEstimate
		]

    static constraints = {
    }
}
