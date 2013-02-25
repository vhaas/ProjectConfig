package testgrails

import org.springframework.dao.DataIntegrityViolationException

class PlannedEffortController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [plannedEffortInstanceList: PlannedEffort.list(params), plannedEffortInstanceTotal: PlannedEffort.count()]
    }

    def create() {
        [plannedEffortInstance: new PlannedEffort(params)]
    }

    def save() {
        def plannedEffortInstance = new PlannedEffort(params)
        if (!plannedEffortInstance.save(flush: true)) {
            render(view: "create", model: [plannedEffortInstance: plannedEffortInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), plannedEffortInstance.id])
        redirect(action: "show", id: plannedEffortInstance.id)
    }

    def show(Long id) {
        def plannedEffortInstance = PlannedEffort.get(id)
        if (!plannedEffortInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), id])
            redirect(action: "list")
            return
        }

        [plannedEffortInstance: plannedEffortInstance]
    }

    def edit(Long id) {
        def plannedEffortInstance = PlannedEffort.get(id)
        if (!plannedEffortInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), id])
            redirect(action: "list")
            return
        }

        [plannedEffortInstance: plannedEffortInstance]
    }

    def update(Long id, Long version) {
        def plannedEffortInstance = PlannedEffort.get(id)
        if (!plannedEffortInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (plannedEffortInstance.version > version) {
                plannedEffortInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'plannedEffort.label', default: 'PlannedEffort')] as Object[],
                          "Another user has updated this PlannedEffort while you were editing")
                render(view: "edit", model: [plannedEffortInstance: plannedEffortInstance])
                return
            }
        }

        plannedEffortInstance.properties = params

        if (!plannedEffortInstance.save(flush: true)) {
            render(view: "edit", model: [plannedEffortInstance: plannedEffortInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), plannedEffortInstance.id])
        redirect(action: "show", id: plannedEffortInstance.id)
    }

    def delete(Long id) {
        def plannedEffortInstance = PlannedEffort.get(id)
        if (!plannedEffortInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), id])
            redirect(action: "list")
            return
        }

        try {
            plannedEffortInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'plannedEffort.label', default: 'PlannedEffort'), id])
            redirect(action: "show", id: id)
        }
    }
}
