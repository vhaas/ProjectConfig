package testgrails

import org.springframework.dao.DataIntegrityViolationException

class SystemChangeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [systemChangeInstanceList: SystemChange.list(params), systemChangeInstanceTotal: SystemChange.count()]
    }

    def create() {
        [systemChangeInstance: new SystemChange(params)]
    }

    def save() {
        def systemChangeInstance = new SystemChange(params)
        if (!systemChangeInstance.save(flush: true)) {
            render(view: "create", model: [systemChangeInstance: systemChangeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), systemChangeInstance.id])
        redirect(action: "show", id: systemChangeInstance.id)
    }

    def show(Long id) {
        def systemChangeInstance = SystemChange.get(id)
        if (!systemChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), id])
            redirect(action: "list")
            return
        }

        [systemChangeInstance: systemChangeInstance]
    }

    def edit(Long id) {
        def systemChangeInstance = SystemChange.get(id)
        if (!systemChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), id])
            redirect(action: "list")
            return
        }

        [systemChangeInstance: systemChangeInstance]
    }

    def update(Long id, Long version) {
        def systemChangeInstance = SystemChange.get(id)
        if (!systemChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (systemChangeInstance.version > version) {
                systemChangeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'systemChange.label', default: 'SystemChange')] as Object[],
                          "Another user has updated this SystemChange while you were editing")
                render(view: "edit", model: [systemChangeInstance: systemChangeInstance])
                return
            }
        }

        systemChangeInstance.properties = params

        if (!systemChangeInstance.save(flush: true)) {
            render(view: "edit", model: [systemChangeInstance: systemChangeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), systemChangeInstance.id])
        redirect(action: "show", id: systemChangeInstance.id)
    }

    def delete(Long id) {
        def systemChangeInstance = SystemChange.get(id)
        if (!systemChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), id])
            redirect(action: "list")
            return
        }

        try {
            systemChangeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'systemChange.label', default: 'SystemChange'), id])
            redirect(action: "show", id: id)
        }
    }
}
