package testgrails

import org.springframework.dao.DataIntegrityViolationException

class EpicController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [epicInstanceList: Epic.list(params), epicInstanceTotal: Epic.count()]
    }

    def create() {
        [epicInstance: new Epic(params)]
    }

    def save() {
        def epicInstance = new Epic(params)
        if (!epicInstance.save(flush: true)) {
            render(view: "create", model: [epicInstance: epicInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'epic.label', default: 'Epic'), epicInstance.id])
        redirect(action: "show", id: epicInstance.id)
    }

    def show(Long id) {
        def epicInstance = Epic.get(id)
        if (!epicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'epic.label', default: 'Epic'), id])
            redirect(action: "list")
            return
        }

        [epicInstance: epicInstance]
    }

    def edit(Long id) {
        def epicInstance = Epic.get(id)
        if (!epicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'epic.label', default: 'Epic'), id])
            redirect(action: "list")
            return
        }

        [epicInstance: epicInstance]
    }

    def update(Long id, Long version) {
        def epicInstance = Epic.get(id)
        if (!epicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'epic.label', default: 'Epic'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (epicInstance.version > version) {
                epicInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'epic.label', default: 'Epic')] as Object[],
                          "Another user has updated this Epic while you were editing")
                render(view: "edit", model: [epicInstance: epicInstance])
                return
            }
        }

        epicInstance.properties = params

        if (!epicInstance.save(flush: true)) {
            render(view: "edit", model: [epicInstance: epicInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'epic.label', default: 'Epic'), epicInstance.id])
        redirect(action: "show", id: epicInstance.id)
    }

    def delete(Long id) {
        def epicInstance = Epic.get(id)
        if (!epicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'epic.label', default: 'Epic'), id])
            redirect(action: "list")
            return
        }

        try {
            epicInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'epic.label', default: 'Epic'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'epic.label', default: 'Epic'), id])
            redirect(action: "show", id: id)
        }
    }
}
