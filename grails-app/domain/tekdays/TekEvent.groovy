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
	
	static searchable = true;
	
    static constraints = {
		name(nullable: false)
		city(nullable: false)
		description(nullable: true, maxSize: 5000)
		organizer(nullable: true)
		venue(nullable: true)
		startDate(nullable: true)
		endDate(nullable: true)
		volunteers(nullable: true)
		sponsorships(nullable: true)
		tasks(nullable: true)
		messages(nullable: true)
    }
}
