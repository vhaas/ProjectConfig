package testgrails

import org.hibernate.envers.Audited

@Audited
class ProjectStaff {
	
	String name	
	
	static belongsTo = Profile
	static hasMany = [
		plannedEfforts: PlannedEffort,
		profiles: Profile
		]

    static constraints = {
    }
}
