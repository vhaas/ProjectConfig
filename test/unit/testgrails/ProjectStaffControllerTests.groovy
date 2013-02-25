package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(ProjectStaffController)
@Mock(ProjectStaff)
class ProjectStaffControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/projectStaff/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.projectStaffInstanceList.size() == 0
        assert model.projectStaffInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.projectStaffInstance != null
    }

    void testSave() {
        controller.save()

        assert model.projectStaffInstance != null
        assert view == '/projectStaff/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/projectStaff/show/1'
        assert controller.flash.message != null
        assert ProjectStaff.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/projectStaff/list'

        populateValidParams(params)
        def projectStaff = new ProjectStaff(params)

        assert projectStaff.save() != null

        params.id = projectStaff.id

        def model = controller.show()

        assert model.projectStaffInstance == projectStaff
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/projectStaff/list'

        populateValidParams(params)
        def projectStaff = new ProjectStaff(params)

        assert projectStaff.save() != null

        params.id = projectStaff.id

        def model = controller.edit()

        assert model.projectStaffInstance == projectStaff
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/projectStaff/list'

        response.reset()

        populateValidParams(params)
        def projectStaff = new ProjectStaff(params)

        assert projectStaff.save() != null

        // test invalid parameters in update
        params.id = projectStaff.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/projectStaff/edit"
        assert model.projectStaffInstance != null

        projectStaff.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/projectStaff/show/$projectStaff.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        projectStaff.clearErrors()

        populateValidParams(params)
        params.id = projectStaff.id
        params.version = -1
        controller.update()

        assert view == "/projectStaff/edit"
        assert model.projectStaffInstance != null
        assert model.projectStaffInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/projectStaff/list'

        response.reset()

        populateValidParams(params)
        def projectStaff = new ProjectStaff(params)

        assert projectStaff.save() != null
        assert ProjectStaff.count() == 1

        params.id = projectStaff.id

        controller.delete()

        assert ProjectStaff.count() == 0
        assert ProjectStaff.get(projectStaff.id) == null
        assert response.redirectedUrl == '/projectStaff/list'
    }
}
