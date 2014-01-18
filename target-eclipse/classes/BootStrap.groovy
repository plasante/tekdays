import tekdays.Sponsor
import tekdays.Sponsorship
import tekdays.TekEvent
import tekdays.TekUser

class BootStrap {

    def init = { servletContext ->
			new TekUser(fullName: 'John Doe',
						userName: 'jdoe',
						password: '12345',
						email: 'jdoe@email.com',
						website: 'blog.jdoe.com',
						bio: '''John has been programming for over 40 years. He has worked
								with every programming language known to man and has settled
								on Groovy.'''
			).save()
				
			new TekUser(fullName: 'John Deere',
						userName: 'jdeere',
						password: '12345',
						email: 'jdeere@email.com',
						website: 'blog.jdeere.com',
						bio: '''John is a top notch Perl programmer and a pretty good
								hand around the farm. If he can't program it he can
								plow it!'''
			).save()
			
			def event1 = new TekEvent(name: 'Gateway Code Camp',
									  city: 'Saint Louis, MO',
									  organizer: TekUser.findByFullName('John Doe'),
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
									  organizer: TekUser.findByFullName('John Deere'),
									  venue: 'SPAM Museum',
									  startDate: new Date('9/1/2014'),
									  endDate: new Date('9/1/2014'),
									  description: '''
													Join the Perl programmers of the Pork Producers
													of America as we hone our skill and ham it up
													a bit.
												   ''')
			
			if (!event2.save()) {
				event2.errors.allErrors.each { error ->
					println "An error occured with event2: ${error}"
				}
			}
			
			def g1 = TekEvent.findByName('Gateway Code Camp')
			g1.addToVolunteers(new TekUser(fullName: 'Sarah Martin',
										   userName: 'sarah',
										   password: '12345',
										   email: 'sarah@email.com',
										   website: 'www.sarah.com',
										   bio: 'Web designer.'))
			g1.addToVolunteers(new TekUser(fullName: 'BillSmith',
										   userName: 'bill',
										   password: '12345',
										   email: 'bill@email.com',
										   website: 'www.bill.com',
										   bio: 'Software developer.'))
			g1.addToRespondents('pierre@email.com')
			g1.addToRespondents('karo@email.com')
			g1.addToRespondents('juby@email.com')
			
			def s1 = new Sponsor(name: 'Congetix',
								 website: 'congetix.com',
								 description: 'Beyond Managed Hosting for your Enterprise').save()
			def s2 = new Sponsor(name: 'Object Computing Incorporated',
								 website: 'ociweb.com',
								 description: 'An OO Software Engineering Company').save()
			def sp1 = new Sponsorship(event: g1,
									  sponsor: s1,
									  contributionType: 'Other',
									  description: 'Cool T-Shirts')
			def sp2 = new Sponsorship(event: g1,
									  sponsor: s2,
									  contributionType: 'Venue',
									  description: 'Will be paying for the Moscone')
			s1.addToSponsorships(sp1)
			s1.save()
			s2.addToSponsorships(sp2)
			s2.save()
			g1.addToSponsorships(sp1)
			g1.addToSponsorships(sp2)
			g1.save()
    }
	
    def destroy = {
    }
}
