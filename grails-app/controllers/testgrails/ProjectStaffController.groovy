package testgrails

import org.springframework.dao.DataIntegrityViolationException

class ProjectStaffController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [projectStaffInstanceList: ProjectStaff.list(params), projectStaffInstanceTotal: ProjectStaff.count()]
    }

    def create() {
        [projectStaffInstance: new ProjectStaff(params)]
    }

    def save() {
        def projectStaffInstance = new ProjectStaff(params)
        if (!projectStaffInstance.save(flush: true)) {
            render(view: "create", model: [projectStaffInstance: projectStaffInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), projectStaffInstance.id])
        redirect(action: "show", id: projectStaffInstance.id)
    }

    def show(Long id) {
        def projectStaffInstance = ProjectStaff.get(id)
        if (!projectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), id])
            redirect(action: "list")
            return
        }

        [projectStaffInstance: projectStaffInstance]
    }

    def edit(Long id) {
        def projectStaffInstance = ProjectStaff.get(id)
        if (!projectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), id])
            redirect(action: "list")
            return
        }

        [projectStaffInstance: projectStaffInstance]
    }

    def update(Long id, Long version) {
        def projectStaffInstance = ProjectStaff.get(id)
        if (!projectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (projectStaffInstance.version > version) {
                projectStaffInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'projectStaff.label', default: 'ProjectStaff')] as Object[],
                          "Another user has updated this ProjectStaff while you were editing")
                render(view: "edit", model: [projectStaffInstance: projectStaffInstance])
                return
            }
        }

        projectStaffInstance.properties = params

        if (!projectStaffInstance.save(flush: true)) {
            render(view: "edit", model: [projectStaffInstance: projectStaffInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), projectStaffInstance.id])
        redirect(action: "show", id: projectStaffInstance.id)
    }

    def delete(Long id) {
        def projectStaffInstance = ProjectStaff.get(id)
        if (!projectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), id])
            redirect(action: "list")
            return
        }

        try {
            projectStaffInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'projectStaff.label', default: 'ProjectStaff'), id])
            redirect(action: "show", id: id)
        }
    }
}
