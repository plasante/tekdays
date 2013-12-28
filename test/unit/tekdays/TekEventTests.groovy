package tekdays



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekEvent)
class TekEventTests {
	void testToStrind() {
		def tekEvent = new TekEvent(name: 'Groovy One',
									city: 'San Francisco, CA',
									organizer: [fullName: 'John Doe'] as TekUser, 	// We mock the organizer with a Map
									venue: 'Moscone Center',
									startDate: new Date('6/2/2014'),
									endDate: new Date('6/5/2014'),
									description: 'This conference will cover all...')
		assertEquals 'Groovy One, San Francisco, CA' , tekEvent.toString()
	}
}
