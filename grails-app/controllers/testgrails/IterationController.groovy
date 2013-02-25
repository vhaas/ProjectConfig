package testgrails

import org.springframework.dao.DataIntegrityViolationException

class IterationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [iterationInstanceList: Iteration.list(params), iterationInstanceTotal: Iteration.count()]
    }

    def create() {
        [iterationInstance: new Iteration(params)]
    }

    def save() {
        def iterationInstance = new Iteration(params)
        if (!iterationInstance.save(flush: true)) {
            render(view: "create", model: [iterationInstance: iterationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'iteration.label', default: 'Iteration'), iterationInstance.id])
        redirect(action: "show", id: iterationInstance.id)
    }

    def show(Long id) {
        def iterationInstance = Iteration.get(id)
        if (!iterationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'iteration.label', default: 'Iteration'), id])
            redirect(action: "list")
            return
        }

        [iterationInstance: iterationInstance]
    }

    def edit(Long id) {
        def iterationInstance = Iteration.get(id)
        if (!iterationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'iteration.label', default: 'Iteration'), id])
            redirect(action: "list")
            return
        }

        [iterationInstance: iterationInstance]
    }

    def update(Long id, Long version) {
        def iterationInstance = Iteration.get(id)
        if (!iterationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'iteration.label', default: 'Iteration'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (iterationInstance.version > version) {
                iterationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'iteration.label', default: 'Iteration')] as Object[],
                          "Another user has updated this Iteration while you were editing")
                render(view: "edit", model: [iterationInstance: iterationInstance])
                return
            }
        }

        iterationInstance.properties = params

        if (!iterationInstance.save(flush: true)) {
            render(view: "edit", model: [iterationInstance: iterationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'iteration.label', default: 'Iteration'), iterationInstance.id])
        redirect(action: "show", id: iterationInstance.id)
    }

    def delete(Long id) {
        def iterationInstance = Iteration.get(id)
        if (!iterationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'iteration.label', default: 'Iteration'), id])
            redirect(action: "list")
            return
        }

        try {
            iterationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'iteration.label', default: 'Iteration'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'iteration.label', default: 'Iteration'), id])
            redirect(action: "show", id: id)
        }
    }
}
