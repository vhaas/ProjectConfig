package testgrails

import org.springframework.dao.DataIntegrityViolationException

class FirstEffortEstimateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [firstEffortEstimateInstanceList: FirstEffortEstimate.list(params), firstEffortEstimateInstanceTotal: FirstEffortEstimate.count()]
    }

    def create() {
        [firstEffortEstimateInstance: new FirstEffortEstimate(params)]
    }

    def save() {
        def firstEffortEstimateInstance = new FirstEffortEstimate(params)
        if (!firstEffortEstimateInstance.save(flush: true)) {
            render(view: "create", model: [firstEffortEstimateInstance: firstEffortEstimateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), firstEffortEstimateInstance.id])
        redirect(action: "show", id: firstEffortEstimateInstance.id)
    }

    def show(Long id) {
        def firstEffortEstimateInstance = FirstEffortEstimate.get(id)
        if (!firstEffortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), id])
            redirect(action: "list")
            return
        }

        [firstEffortEstimateInstance: firstEffortEstimateInstance]
    }

    def edit(Long id) {
        def firstEffortEstimateInstance = FirstEffortEstimate.get(id)
        if (!firstEffortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), id])
            redirect(action: "list")
            return
        }

        [firstEffortEstimateInstance: firstEffortEstimateInstance]
    }

    def update(Long id, Long version) {
        def firstEffortEstimateInstance = FirstEffortEstimate.get(id)
        if (!firstEffortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (firstEffortEstimateInstance.version > version) {
                firstEffortEstimateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate')] as Object[],
                          "Another user has updated this FirstEffortEstimate while you were editing")
                render(view: "edit", model: [firstEffortEstimateInstance: firstEffortEstimateInstance])
                return
            }
        }

        firstEffortEstimateInstance.properties = params

        if (!firstEffortEstimateInstance.save(flush: true)) {
            render(view: "edit", model: [firstEffortEstimateInstance: firstEffortEstimateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), firstEffortEstimateInstance.id])
        redirect(action: "show", id: firstEffortEstimateInstance.id)
    }

    def delete(Long id) {
        def firstEffortEstimateInstance = FirstEffortEstimate.get(id)
        if (!firstEffortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), id])
            redirect(action: "list")
            return
        }

        try {
            firstEffortEstimateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'firstEffortEstimate.label', default: 'FirstEffortEstimate'), id])
            redirect(action: "show", id: id)
        }
    }
}
