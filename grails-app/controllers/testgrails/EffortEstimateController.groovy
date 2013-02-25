package testgrails

import org.springframework.dao.DataIntegrityViolationException

class EffortEstimateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [effortEstimateInstanceList: EffortEstimate.list(params), effortEstimateInstanceTotal: EffortEstimate.count()]
    }

    def create() {
        [effortEstimateInstance: new EffortEstimate(params)]
    }

    def save() {
        def effortEstimateInstance = new EffortEstimate(params)
        if (!effortEstimateInstance.save(flush: true)) {
            render(view: "create", model: [effortEstimateInstance: effortEstimateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), effortEstimateInstance.id])
        redirect(action: "show", id: effortEstimateInstance.id)
    }

    def show(Long id) {
        def effortEstimateInstance = EffortEstimate.get(id)
        if (!effortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), id])
            redirect(action: "list")
            return
        }

        [effortEstimateInstance: effortEstimateInstance]
    }

    def edit(Long id) {
        def effortEstimateInstance = EffortEstimate.get(id)
        if (!effortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), id])
            redirect(action: "list")
            return
        }

        [effortEstimateInstance: effortEstimateInstance]
    }

    def update(Long id, Long version) {
        def effortEstimateInstance = EffortEstimate.get(id)
        if (!effortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (effortEstimateInstance.version > version) {
                effortEstimateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'effortEstimate.label', default: 'EffortEstimate')] as Object[],
                          "Another user has updated this EffortEstimate while you were editing")
                render(view: "edit", model: [effortEstimateInstance: effortEstimateInstance])
                return
            }
        }

        effortEstimateInstance.properties = params

        if (!effortEstimateInstance.save(flush: true)) {
            render(view: "edit", model: [effortEstimateInstance: effortEstimateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), effortEstimateInstance.id])
        redirect(action: "show", id: effortEstimateInstance.id)
    }

    def delete(Long id) {
        def effortEstimateInstance = EffortEstimate.get(id)
        if (!effortEstimateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), id])
            redirect(action: "list")
            return
        }

        try {
            effortEstimateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'effortEstimate.label', default: 'EffortEstimate'), id])
            redirect(action: "show", id: id)
        }
    }
}
