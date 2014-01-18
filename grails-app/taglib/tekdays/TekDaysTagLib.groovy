package tekdays

class TekDaysTagLib {
	def loginToggle = {
        out << "<div>"
        if (session.user) {
            out << "<span style='float:left'>"
            out << "Welcome ${session.user}."
            out << "</span><span style='float:right; margin-right:10px'>"
            out << "<a href='${createLink(controller:'tekUser', action:'logout')}'>"
            out << "Logout</a></span>"
        }
        else {
            out << "<span style='float:right; margin-right:10px'>"
            out << "<a href='${createLink(controller:'tekUser', action:'login')}'>"
            out << "Login </a></span>"
        }
        out << "</div><br/>"
    }
}