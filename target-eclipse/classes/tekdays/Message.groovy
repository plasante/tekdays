package tekdays

class Message {
	String subject
	String content
	Message parent
	TekEvent event
	TekUser author
	
    static constraints = {
		subject(blank: false)
		content(blank: false, maxSize: 2000)
		parent(nullable: true)
		author(nullable: false)
    }
	
	// A message will belong to only one TekEvent.
	// If that TekEvent goes away this Message will also go away.
	static belongsTo = TekEvent
}
