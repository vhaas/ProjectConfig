package testgrails

import org.springframework.dao.DataIntegrityViolationException

class RoadMapController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [roadMapInstanceList: RoadMap.list(params), roadMapInstanceTotal: RoadMap.count()]
    }

    def create() {
        [roadMapInstance: new RoadMap(params)]
    }

    def save() {
        def roadMapInstance = new RoadMap(params)
        if (!roadMapInstance.save(flush: true)) {
            render(view: "create", model: [roadMapInstance: roadMapInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), roadMapInstance.id])
        redirect(action: "show", id: roadMapInstance.id)
    }

    def show(Long id) {
        def roadMapInstance = RoadMap.get(id)
        if (!roadMapInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), id])
            redirect(action: "list")
            return
        }

        [roadMapInstance: roadMapInstance]
    }

    def edit(Long id) {
        def roadMapInstance = RoadMap.get(id)
        if (!roadMapInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), id])
            redirect(action: "list")
            return
        }

        [roadMapInstance: roadMapInstance]
    }

    def update(Long id, Long version) {
        def roadMapInstance = RoadMap.get(id)
        if (!roadMapInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (roadMapInstance.version > version) {
                roadMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'roadMap.label', default: 'RoadMap')] as Object[],
                          "Another user has updated this RoadMap while you were editing")
                render(view: "edit", model: [roadMapInstance: roadMapInstance])
                return
            }
        }

        roadMapInstance.properties = params

        if (!roadMapInstance.save(flush: true)) {
            render(view: "edit", model: [roadMapInstance: roadMapInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), roadMapInstance.id])
        redirect(action: "show", id: roadMapInstance.id)
    }

    def delete(Long id) {
        def roadMapInstance = RoadMap.get(id)
        if (!roadMapInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), id])
            redirect(action: "list")
            return
        }

        try {
            roadMapInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'roadMap.label', default: 'RoadMap'), id])
            redirect(action: "show", id: id)
        }
    }
}
