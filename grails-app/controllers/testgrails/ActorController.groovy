package testgrails

import org.springframework.dao.DataIntegrityViolationException

class ActorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [actorInstanceList: Actor.list(params), actorInstanceTotal: Actor.count()]
    }

    def create() {
        [actorInstance: new Actor(params)]
    }

    def save() {
        def actorInstance = new Actor(params)
        if (!actorInstance.save(flush: true)) {
            render(view: "create", model: [actorInstance: actorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'actor.label', default: 'Actor'), actorInstance.id])
        redirect(action: "show", id: actorInstance.id)
    }

    def show(Long id) {
        def actorInstance = Actor.get(id)
        if (!actorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actor.label', default: 'Actor'), id])
            redirect(action: "list")
            return
        }

        [actorInstance: actorInstance]
    }

    def edit(Long id) {
        def actorInstance = Actor.get(id)
        if (!actorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actor.label', default: 'Actor'), id])
            redirect(action: "list")
            return
        }

        [actorInstance: actorInstance]
    }

    def update(Long id, Long version) {
        def actorInstance = Actor.get(id)
        if (!actorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actor.label', default: 'Actor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (actorInstance.version > version) {
                actorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'actor.label', default: 'Actor')] as Object[],
                          "Another user has updated this Actor while you were editing")
                render(view: "edit", model: [actorInstance: actorInstance])
                return
            }
        }

        actorInstance.properties = params

        if (!actorInstance.save(flush: true)) {
            render(view: "edit", model: [actorInstance: actorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'actor.label', default: 'Actor'), actorInstance.id])
        redirect(action: "show", id: actorInstance.id)
    }

    def delete(Long id) {
        def actorInstance = Actor.get(id)
        if (!actorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'actor.label', default: 'Actor'), id])
            redirect(action: "list")
            return
        }

        try {
            actorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'actor.label', default: 'Actor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'actor.label', default: 'Actor'), id])
            redirect(action: "show", id: id)
        }
    }
}
