package testgrails

import org.springframework.dao.DataIntegrityViolationException

class PreConditionsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [preConditionsInstanceList: PreConditions.list(params), preConditionsInstanceTotal: PreConditions.count()]
    }

    def create() {
        [preConditionsInstance: new PreConditions(params)]
    }

    def save() {
        def preConditionsInstance = new PreConditions(params)
        if (!preConditionsInstance.save(flush: true)) {
            render(view: "create", model: [preConditionsInstance: preConditionsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), preConditionsInstance.id])
        redirect(action: "show", id: preConditionsInstance.id)
    }

    def show(Long id) {
        def preConditionsInstance = PreConditions.get(id)
        if (!preConditionsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), id])
            redirect(action: "list")
            return
        }

        [preConditionsInstance: preConditionsInstance]
    }

    def edit(Long id) {
        def preConditionsInstance = PreConditions.get(id)
        if (!preConditionsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), id])
            redirect(action: "list")
            return
        }

        [preConditionsInstance: preConditionsInstance]
    }

    def update(Long id, Long version) {
        def preConditionsInstance = PreConditions.get(id)
        if (!preConditionsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (preConditionsInstance.version > version) {
                preConditionsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'preConditions.label', default: 'PreConditions')] as Object[],
                          "Another user has updated this PreConditions while you were editing")
                render(view: "edit", model: [preConditionsInstance: preConditionsInstance])
                return
            }
        }

        preConditionsInstance.properties = params

        if (!preConditionsInstance.save(flush: true)) {
            render(view: "edit", model: [preConditionsInstance: preConditionsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), preConditionsInstance.id])
        redirect(action: "show", id: preConditionsInstance.id)
    }

    def delete(Long id) {
        def preConditionsInstance = PreConditions.get(id)
        if (!preConditionsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), id])
            redirect(action: "list")
            return
        }

        try {
            preConditionsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'preConditions.label', default: 'PreConditions'), id])
            redirect(action: "show", id: id)
        }
    }
}
