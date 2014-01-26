class SecurityFilters {
	def filters = {
		// This filter will be called for all actions.
		doLogin(controller: '*', action: '*') {
			before = {
				// controllerName and actionName are injected by Grails.
				if (!controllerName) return true
				def allowedActions = ['show', 'index', 'list', 'login', 'validate', 'search']
				// If we do not have a user and the current action is not int he allowedActions list then redirect
				if (!session.user && !allowedActions.contains(actionName)) {
					redirect(controller: 'tekUser', action: 'login', params: ['cName': controllerName, 'aName': actionName])
					return false
				}
			}
		}
	}
}