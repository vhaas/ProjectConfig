package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(InterfaceChangeController)
@Mock(InterfaceChange)
class InterfaceChangeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/interfaceChange/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.interfaceChangeInstanceList.size() == 0
        assert model.interfaceChangeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.interfaceChangeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.interfaceChangeInstance != null
        assert view == '/interfaceChange/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/interfaceChange/show/1'
        assert controller.flash.message != null
        assert InterfaceChange.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/interfaceChange/list'

        populateValidParams(params)
        def interfaceChange = new InterfaceChange(params)

        assert interfaceChange.save() != null

        params.id = interfaceChange.id

        def model = controller.show()

        assert model.interfaceChangeInstance == interfaceChange
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/interfaceChange/list'

        populateValidParams(params)
        def interfaceChange = new InterfaceChange(params)

        assert interfaceChange.save() != null

        params.id = interfaceChange.id

        def model = controller.edit()

        assert model.interfaceChangeInstance == interfaceChange
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/interfaceChange/list'

        response.reset()

        populateValidParams(params)
        def interfaceChange = new InterfaceChange(params)

        assert interfaceChange.save() != null

        // test invalid parameters in update
        params.id = interfaceChange.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/interfaceChange/edit"
        assert model.interfaceChangeInstance != null

        interfaceChange.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/interfaceChange/show/$interfaceChange.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        interfaceChange.clearErrors()

        populateValidParams(params)
        params.id = interfaceChange.id
        params.version = -1
        controller.update()

        assert view == "/interfaceChange/edit"
        assert model.interfaceChangeInstance != null
        assert model.interfaceChangeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/interfaceChange/list'

        response.reset()

        populateValidParams(params)
        def interfaceChange = new InterfaceChange(params)

        assert interfaceChange.save() != null
        assert InterfaceChange.count() == 1

        params.id = interfaceChange.id

        controller.delete()

        assert InterfaceChange.count() == 0
        assert InterfaceChange.get(interfaceChange.id) == null
        assert response.redirectedUrl == '/interfaceChange/list'
    }
}
