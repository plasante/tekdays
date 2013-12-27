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
			
		}
		
		def event2 = new TekEvent()
		
		if (!event2.save()) {
			
		}
    }
	
    def destroy = {
    }
}
