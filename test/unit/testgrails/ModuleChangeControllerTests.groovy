package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(ModuleChangeController)
@Mock(ModuleChange)
class ModuleChangeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/moduleChange/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.moduleChangeInstanceList.size() == 0
        assert model.moduleChangeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.moduleChangeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.moduleChangeInstance != null
        assert view == '/moduleChange/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/moduleChange/show/1'
        assert controller.flash.message != null
        assert ModuleChange.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/moduleChange/list'

        populateValidParams(params)
        def moduleChange = new ModuleChange(params)

        assert moduleChange.save() != null

        params.id = moduleChange.id

        def model = controller.show()

        assert model.moduleChangeInstance == moduleChange
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/moduleChange/list'

        populateValidParams(params)
        def moduleChange = new ModuleChange(params)

        assert moduleChange.save() != null

        params.id = moduleChange.id

        def model = controller.edit()

        assert model.moduleChangeInstance == moduleChange
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/moduleChange/list'

        response.reset()

        populateValidParams(params)
        def moduleChange = new ModuleChange(params)

        assert moduleChange.save() != null

        // test invalid parameters in update
        params.id = moduleChange.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/moduleChange/edit"
        assert model.moduleChangeInstance != null

        moduleChange.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/moduleChange/show/$moduleChange.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        moduleChange.clearErrors()

        populateValidParams(params)
        params.id = moduleChange.id
        params.version = -1
        controller.update()

        assert view == "/moduleChange/edit"
        assert model.moduleChangeInstance != null
        assert model.moduleChangeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/moduleChange/list'

        response.reset()

        populateValidParams(params)
        def moduleChange = new ModuleChange(params)

        assert moduleChange.save() != null
        assert ModuleChange.count() == 1

        params.id = moduleChange.id

        controller.delete()

        assert ModuleChange.count() == 0
        assert ModuleChange.get(moduleChange.id) == null
        assert response.redirectedUrl == '/moduleChange/list'
    }
}
