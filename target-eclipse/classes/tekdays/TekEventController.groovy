package tekdays

import org.springframework.dao.DataIntegrityViolationException

// Nothing to extend and nothing to implement.
class TekEventController {

	// The delete, save, and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def taskService // An instance of that service class will be injected into our controller at runtime.
	
	// default action
    def index() {
		// redirect(controller: "...", action: "...", params: "...")
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
		// This is to make sure a maximum of 100 TekEvent will be listed in the view.
        params.max = Math.min(max ?: 10, 100)
        [tekEventInstanceList: TekEvent.list(params), tekEventInstanceTotal: TekEvent.count()]
    }

	
    def create() {
        [tekEventInstance: new TekEvent(params)]
    }

    def save() {
        def tekEventInstance = new TekEvent(params)
        if (!tekEventInstance.save(flush: true)) {
            render(view: "create", model: [tekEventInstance: tekEventInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])
        taskService.addDefaultTasks(tekEventInstance)
		redirect(action: "show", id: tekEventInstance.id)
    }

    def show(Long id) {
		// Retrieving the TekEvent referred to by the id parameter.
        def tekEventInstance = TekEvent.get(id)
		
        if (!tekEventInstance) {
			// If no domain class instance exists with the id passed in,
			// an error message is stored in the flash Map, and the user
			// is redirected to the list view.
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), id])
            redirect(action: "list")
            return
        }

		// The show action will render the show view.
        [tekEventInstance: tekEventInstance]
    }

    def edit(Long id) {
        def tekEventInstance = TekEvent.get(id)
        if (!tekEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), id])
            redirect(action: "list")
            return
        }

        [tekEventInstance: tekEventInstance]
    }

    def update(Long id, Long version) {
        def tekEventInstance = TekEvent.get(id)
        if (!tekEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), id])
            redirect(action: "list")
            return
        }

		// Performing optimistic concurrency checking.
        if (version != null) {
            if (tekEventInstance.version > version) {
                tekEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tekEvent.label', default: 'TekEvent')] as Object[],
                          "Another user has updated this TekEvent while you were editing")
                render(view: "edit", model: [tekEventInstance: tekEventInstance])
                return
            }
        }

		// Assigning all the values from the edit view to the appropriate property of the TekEvent instance.
		// This is Grails data binding in action.
        tekEventInstance.properties = params

        if (!tekEventInstance.save(flush: true)) {
            render(view: "edit", model: [tekEventInstance: tekEventInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])
        redirect(action: "show", id: tekEventInstance.id)
    }

	// This action must be called via a POST method.
    def delete(Long id) {
        def tekEventInstance = TekEvent.get(id)
        if (!tekEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), id])
            redirect(action: "list")
            return
        }

        try {
            tekEventInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), id])
            redirect(action: "show", id: id)
        }
    }
}
