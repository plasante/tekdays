import tekdays.TekEvent

class BootStrap {

    def init = { servletContext ->
		def event1 = new TekEvent(name: 'Gateway Code Camp',
								  city: 'Saint Louis, MO',
								  organizer: 'John Doe',
								  venue: 'TBD',
								  startDate: new Date('9/19/2014'),
								  endDate: new Date('9/19/2014'),
								  description: '''
												  This conference will bring coders from
												  across platforms, languages, and industries
												  together for an exciting day of tips, tricks,
												  and tech! Stay sharp! Stay at the top of your
												  game! But, don't stay home!
											   ''')
		
		if (!event1.save()) {
			event1.errors.allErrors.each { error ->
				println "An error occured with event1: ${error}"
			}
		}
		
		def event2 = new TekEvent(name: 'Perl Before Swing',
								  city: 'Austin, MN',
								  organizer: 'John Deere',
								  venue: 'SPAM Museum',
								  startDate: new Date('9/1/2014'),
								  endDate: new Date('9/1/2014'),
								  description: '''
												Join the Perl programmers of the Pork Producers
												of America as we hone our skill and ham it up
												a bit.
											   ''')
		
		if (!event2.save()) {
			
		}
    }
	
    def destroy = {
    }
}
