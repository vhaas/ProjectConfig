package testgrails

import org.springframework.dao.DataIntegrityViolationException

class ModuleChangeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [moduleChangeInstanceList: ModuleChange.list(params), moduleChangeInstanceTotal: ModuleChange.count()]
    }

    def create() {
        [moduleChangeInstance: new ModuleChange(params)]
    }

    def save() {
        def moduleChangeInstance = new ModuleChange(params)
        if (!moduleChangeInstance.save(flush: true)) {
            render(view: "create", model: [moduleChangeInstance: moduleChangeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), moduleChangeInstance.id])
        redirect(action: "show", id: moduleChangeInstance.id)
    }

    def show(Long id) {
        def moduleChangeInstance = ModuleChange.get(id)
        if (!moduleChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), id])
            redirect(action: "list")
            return
        }

        [moduleChangeInstance: moduleChangeInstance]
    }

    def edit(Long id) {
        def moduleChangeInstance = ModuleChange.get(id)
        if (!moduleChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), id])
            redirect(action: "list")
            return
        }

        [moduleChangeInstance: moduleChangeInstance]
    }

    def update(Long id, Long version) {
        def moduleChangeInstance = ModuleChange.get(id)
        if (!moduleChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (moduleChangeInstance.version > version) {
                moduleChangeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'moduleChange.label', default: 'ModuleChange')] as Object[],
                          "Another user has updated this ModuleChange while you were editing")
                render(view: "edit", model: [moduleChangeInstance: moduleChangeInstance])
                return
            }
        }

        moduleChangeInstance.properties = params

        if (!moduleChangeInstance.save(flush: true)) {
            render(view: "edit", model: [moduleChangeInstance: moduleChangeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), moduleChangeInstance.id])
        redirect(action: "show", id: moduleChangeInstance.id)
    }

    def delete(Long id) {
        def moduleChangeInstance = ModuleChange.get(id)
        if (!moduleChangeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), id])
            redirect(action: "list")
            return
        }

        try {
            moduleChangeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'moduleChange.label', default: 'ModuleChange'), id])
            redirect(action: "show", id: id)
        }
    }
}
