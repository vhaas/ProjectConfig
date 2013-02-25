package testgrails

import org.springframework.dao.DataIntegrityViolationException

class InterfaceChangeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [interfaceChangeInstanceList: InterfaceChange.list(params), interfaceChangeInstanceTotal: InterfaceChange.count()]
    }

    def create() {
        [interfaceChangeInstance: new InterfaceChange(params)]
    }

    def save() {
        def interfaceChangeInstance = new InterfaceChange(params)
        if (!interfaceChangeInstance.save(flush: true)) {
            render(view: "create", model: [interfaceChangeInstance: interfaceChangeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), interfaceChangeInstance.id])
        redirect(action: "show", id: interfaceChangeInstance.id)
    }

    def show(Long id) {
        def interfaceChangeInstance = InterfaceChange.get(id)
        if (!interfaceChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), id])
            redirect(action: "list")
            return
        }

        [interfaceChangeInstance: interfaceChangeInstance]
    }

    def edit(Long id) {
        def interfaceChangeInstance = InterfaceChange.get(id)
        if (!interfaceChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), id])
            redirect(action: "list")
            return
        }

        [interfaceChangeInstance: interfaceChangeInstance]
    }

    def update(Long id, Long version) {
        def interfaceChangeInstance = InterfaceChange.get(id)
        if (!interfaceChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (interfaceChangeInstance.version > version) {
                interfaceChangeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'interfaceChange.label', default: 'InterfaceChange')] as Object[],
                          "Another user has updated this InterfaceChange while you were editing")
                render(view: "edit", model: [interfaceChangeInstance: interfaceChangeInstance])
                return
            }
        }

        interfaceChangeInstance.properties = params

        if (!interfaceChangeInstance.save(flush: true)) {
            render(view: "edit", model: [interfaceChangeInstance: interfaceChangeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), interfaceChangeInstance.id])
        redirect(action: "show", id: interfaceChangeInstance.id)
    }

    def delete(Long id) {
        def interfaceChangeInstance = InterfaceChange.get(id)
        if (!interfaceChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), id])
            redirect(action: "list")
            return
        }

        try {
            interfaceChangeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'interfaceChange.label', default: 'InterfaceChange'), id])
            redirect(action: "show", id: id)
        }
    }
}
