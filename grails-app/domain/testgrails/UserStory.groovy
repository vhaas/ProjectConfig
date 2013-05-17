package testgrails

class UserStory {
	
	String name
	String description
	String goal
	String benefit	
	
	Epic epic
	Role role
	
	static belongsTo = MileStone
	static hasMany = [
		systemChanges: SystemChange,
		requiredUserStories: UserStory,
		mileStones: MileStone		
		]	

    static constraints = {
		epic nullable: true
		role nullable: true
    }	
	
	public transformToMap() {
		return [
				id: id,
				name: name,
				description: description,
				goal: goal,
				benefit: benefit,
				mile_stone_ids: mileStones.collect{it.id},
				epic_id: epic ? epic.id : '',
				role_id: role ? role.id : ''
		]			
	}
	
	public String getMultipleRoot() {
		return "user_stories"
	}
}
