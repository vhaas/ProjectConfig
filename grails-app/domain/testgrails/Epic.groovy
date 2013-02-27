package testgrails

class Epic {
	
	String name
	String description
	
	static hasMany = [
		userStories: UserStory
		]

    static constraints = {
    }
	
	static namedQueries = {
		findEpicByIdFetchUserStories {
			eq "id", id()
			fetchMode("userStories", join)
		}
	}
}
