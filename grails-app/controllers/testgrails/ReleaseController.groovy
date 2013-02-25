package testgrails

import org.springframework.dao.DataIntegrityViolationException

class ReleaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [releaseInstanceList: Release.list(params), releaseInstanceTotal: Release.count()]
    }

    def create() {
        [releaseInstance: new Release(params)]
    }

    def save() {
        def releaseInstance = new Release(params)
        if (!releaseInstance.save(flush: true)) {
            render(view: "create", model: [releaseInstance: releaseInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'release.label', default: 'Release'), releaseInstance.id])
        redirect(action: "show", id: releaseInstance.id)
    }

    def show(Long id) {
        def releaseInstance = Release.get(id)
        if (!releaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'release.label', default: 'Release'), id])
            redirect(action: "list")
            return
        }

        [releaseInstance: releaseInstance]
    }

    def edit(Long id) {
        def releaseInstance = Release.get(id)
        if (!releaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'release.label', default: 'Release'), id])
            redirect(action: "list")
            return
        }

        [releaseInstance: releaseInstance]
    }

    def update(Long id, Long version) {
        def releaseInstance = Release.get(id)
        if (!releaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'release.label', default: 'Release'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (releaseInstance.version > version) {
                releaseInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'release.label', default: 'Release')] as Object[],
                          "Another user has updated this Release while you were editing")
                render(view: "edit", model: [releaseInstance: releaseInstance])
                return
            }
        }

        releaseInstance.properties = params

        if (!releaseInstance.save(flush: true)) {
            render(view: "edit", model: [releaseInstance: releaseInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'release.label', default: 'Release'), releaseInstance.id])
        redirect(action: "show", id: releaseInstance.id)
    }

    def delete(Long id) {
        def releaseInstance = Release.get(id)
        if (!releaseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'release.label', default: 'Release'), id])
            redirect(action: "list")
            return
        }

        try {
            releaseInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'release.label', default: 'Release'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'release.label', default: 'Release'), id])
            redirect(action: "show", id: id)
        }
    }
}
