package testgrails

import org.hibernate.envers.Audited

@Audited
class ModuleChange {
	
	String name
	String adaptionType
	String adaptionAspect
	
	SystemChange systemChange
	EffortEstimate effortEstimate
	
	static hasMany = [
		interfaceChanges: InterfaceChange,
		useCases: UseCase,
		requiredModules: ModuleChange
		]

    static constraints = {
    }
}
