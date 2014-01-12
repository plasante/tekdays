package tekdays

import org.springframework.dao.DataIntegrityViolationException

class TekUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def login = {
		
	}
	
	def validate = {
		def user = TekUser.findByUserName(params.username)
		if (user && user.password == params.password) {
			session.user = user
			redirect(controller: 'tekEvent', action: 'list')
		} else {
			flash.message = "Invalid username and password."
			render(view: 'login')
		}
	}
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tekUserInstanceList: TekUser.list(params), tekUserInstanceTotal: TekUser.count()]
    }

    def create() {
        [tekUserInstance: new TekUser(params)]
    }

    def save() {
        def tekUserInstance = new TekUser(params)
        if (!tekUserInstance.save(flush: true)) {
            render(view: "create", model: [tekUserInstance: tekUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUserInstance.id])
        redirect(action: "show", id: tekUserInstance.id)
    }

    def show(Long id) {
        def tekUserInstance = TekUser.get(id)
        if (!tekUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), id])
            redirect(action: "list")
            return
        }

        [tekUserInstance: tekUserInstance]
    }

    def edit(Long id) {
        def tekUserInstance = TekUser.get(id)
        if (!tekUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), id])
            redirect(action: "list")
            return
        }

        [tekUserInstance: tekUserInstance]
    }

    def update(Long id, Long version) {
        def tekUserInstance = TekUser.get(id)
        if (!tekUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tekUserInstance.version > version) {
                tekUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tekUser.label', default: 'TekUser')] as Object[],
                          "Another user has updated this TekUser while you were editing")
                render(view: "edit", model: [tekUserInstance: tekUserInstance])
                return
            }
        }

        tekUserInstance.properties = params

        if (!tekUserInstance.save(flush: true)) {
            render(view: "edit", model: [tekUserInstance: tekUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUserInstance.id])
        redirect(action: "show", id: tekUserInstance.id)
    }

    def delete(Long id) {
        def tekUserInstance = TekUser.get(id)
        if (!tekUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), id])
            redirect(action: "list")
            return
        }

        try {
            tekUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tekUser.label', default: 'TekUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tekUser.label', default: 'TekUser'), id])
            redirect(action: "show", id: id)
        }
    }
}
