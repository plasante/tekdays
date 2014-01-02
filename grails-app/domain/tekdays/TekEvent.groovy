package tekdays

class TekEvent {
	String city
	String name
	TekUser organizer	// A TekEvent has a TekUser, but the TekUser doesn't know anything about the TekEvent.
	String venue
	Date startDate
	Date endDate
	String description
	
	static hasMany = [volunteers: TekUser, 
					  respondents: String,
					  sponsorships: Sponsorship,
					  tasks: Task,
					  messages: Message]
	
	String toString() {
		"$name, $city"
	}
	
    static constraints = {
		name()
		city()
		description(maxSize:5000)
		organizer()
		venue()
		startDate()
		endDate()
		volunteers(nullable: true)
		sponsorships(nullable: true)
		tasks(nullable: true)
		messages(nullable: true)
    }
}
