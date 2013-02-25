package testgrails

import org.springframework.dao.DataIntegrityViolationException

class UseCaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [useCaseInstanceList: UseCase.list(params), useCaseInstanceTotal: UseCase.count()]
    }

    def create() {
        [useCaseInstance: new UseCase(params)]
    }

    def save() {
        def useCaseInstance = new UseCase(params)
        if (!useCaseInstance.save(flush: true)) {
            render(view: "create", model: [useCaseInstance: useCaseInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'useCase.label', default: 'UseCase'), useCaseInstance.id])
        redirect(action: "show", id: useCaseInstance.id)
    }

    def show(Long id) {
        def useCaseInstance = UseCase.get(id)
        if (!useCaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'useCase.label', default: 'UseCase'), id])
            redirect(action: "list")
            return
        }

        [useCaseInstance: useCaseInstance]
    }

    def edit(Long id) {
        def useCaseInstance = UseCase.get(id)
        if (!useCaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'useCase.label', default: 'UseCase'), id])
            redirect(action: "list")
            return
        }

        [useCaseInstance: useCaseInstance]
    }

    def update(Long id, Long version) {
        def useCaseInstance = UseCase.get(id)
        if (!useCaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'useCase.label', default: 'UseCase'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (useCaseInstance.version > version) {
                useCaseInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'useCase.label', default: 'UseCase')] as Object[],
                          "Another user has updated this UseCase while you were editing")
                render(view: "edit", model: [useCaseInstance: useCaseInstance])
                return
            }
        }

        useCaseInstance.properties = params

        if (!useCaseInstance.save(flush: true)) {
            render(view: "edit", model: [useCaseInstance: useCaseInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'useCase.label', default: 'UseCase'), useCaseInstance.id])
        redirect(action: "show", id: useCaseInstance.id)
    }

    def delete(Long id) {
        def useCaseInstance = UseCase.get(id)
        if (!useCaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'useCase.label', default: 'UseCase'), id])
            redirect(action: "list")
            return
        }

        try {
            useCaseInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'useCase.label', default: 'UseCase'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'useCase.label', default: 'UseCase'), id])
            redirect(action: "show", id: id)
        }
    }
}
