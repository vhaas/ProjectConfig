package testgrails

import org.springframework.dao.DataIntegrityViolationException

class UserStoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userStoryInstanceList: UserStory.list(params), userStoryInstanceTotal: UserStory.count()]
    }

    def create() {
        [userStoryInstance: new UserStory(params)]
    }

    def save() {
        def userStoryInstance = new UserStory(params)
        if (!userStoryInstance.save(flush: true)) {
            render(view: "create", model: [userStoryInstance: userStoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userStory.label', default: 'UserStory'), userStoryInstance.id])
        redirect(action: "show", id: userStoryInstance.id)
    }

    def show(Long id) {
        def userStoryInstance = UserStory.get(id)
        if (!userStoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userStory.label', default: 'UserStory'), id])
            redirect(action: "list")
            return
        }

        [userStoryInstance: userStoryInstance]
    }

    def edit(Long id) {
        def userStoryInstance = UserStory.get(id)
        if (!userStoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userStory.label', default: 'UserStory'), id])
            redirect(action: "list")
            return
        }

        [userStoryInstance: userStoryInstance]
    }

    def update(Long id, Long version) {
        def userStoryInstance = UserStory.get(id)
        if (!userStoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userStory.label', default: 'UserStory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userStoryInstance.version > version) {
                userStoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userStory.label', default: 'UserStory')] as Object[],
                          "Another user has updated this UserStory while you were editing")
                render(view: "edit", model: [userStoryInstance: userStoryInstance])
                return
            }
        }

        userStoryInstance.properties = params

        if (!userStoryInstance.save(flush: true)) {
            render(view: "edit", model: [userStoryInstance: userStoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userStory.label', default: 'UserStory'), userStoryInstance.id])
        redirect(action: "show", id: userStoryInstance.id)
    }

    def delete(Long id) {
        def userStoryInstance = UserStory.get(id)
        if (!userStoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userStory.label', default: 'UserStory'), id])
            redirect(action: "list")
            return
        }

        try {
            userStoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userStory.label', default: 'UserStory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userStory.label', default: 'UserStory'), id])
            redirect(action: "show", id: id)
        }
    }
}
