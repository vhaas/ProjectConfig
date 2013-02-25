package testgrails

import org.springframework.dao.DataIntegrityViolationException

class ScenarioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scenarioInstanceList: Scenario.list(params), scenarioInstanceTotal: Scenario.count()]
    }

    def create() {
        [scenarioInstance: new Scenario(params)]
    }

    def save() {
        def scenarioInstance = new Scenario(params)
        if (!scenarioInstance.save(flush: true)) {
            render(view: "create", model: [scenarioInstance: scenarioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scenario.label', default: 'Scenario'), scenarioInstance.id])
        redirect(action: "show", id: scenarioInstance.id)
    }

    def show(Long id) {
        def scenarioInstance = Scenario.get(id)
        if (!scenarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scenario.label', default: 'Scenario'), id])
            redirect(action: "list")
            return
        }

        [scenarioInstance: scenarioInstance]
    }

    def edit(Long id) {
        def scenarioInstance = Scenario.get(id)
        if (!scenarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scenario.label', default: 'Scenario'), id])
            redirect(action: "list")
            return
        }

        [scenarioInstance: scenarioInstance]
    }

    def update(Long id, Long version) {
        def scenarioInstance = Scenario.get(id)
        if (!scenarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scenario.label', default: 'Scenario'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scenarioInstance.version > version) {
                scenarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scenario.label', default: 'Scenario')] as Object[],
                          "Another user has updated this Scenario while you were editing")
                render(view: "edit", model: [scenarioInstance: scenarioInstance])
                return
            }
        }

        scenarioInstance.properties = params

        if (!scenarioInstance.save(flush: true)) {
            render(view: "edit", model: [scenarioInstance: scenarioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scenario.label', default: 'Scenario'), scenarioInstance.id])
        redirect(action: "show", id: scenarioInstance.id)
    }

    def delete(Long id) {
        def scenarioInstance = Scenario.get(id)
        if (!scenarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scenario.label', default: 'Scenario'), id])
            redirect(action: "list")
            return
        }

        try {
            scenarioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scenario.label', default: 'Scenario'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scenario.label', default: 'Scenario'), id])
            redirect(action: "show", id: id)
        }
    }
}
