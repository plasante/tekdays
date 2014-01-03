package tekdays

class TekUser {
	String fullName
	String userName
	String password
	String email
	String website
	String bio
	
	String toString() { fullName }
	
    static constraints = {
		fullName(nullable: false)
		userName(nullable: false)
		email(nullable: true)
		website(nullable: true)
		bio(nullable: true, maxSize: 5000)
    }
}
