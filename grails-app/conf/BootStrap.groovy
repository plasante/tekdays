import tekdays.TekEvent

class BootStrap {

    def init = { servletContext ->
		def event1 = new TekEvent()
		
		if (!event1.save()) {
			
		}
		
		def event2 = new TekEvent()
		
		if (!event2.save()) {
			
		}
    }
	
    def destroy = {
    }
}
