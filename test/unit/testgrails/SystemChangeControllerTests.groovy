package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(SystemChangeController)
@Mock(SystemChange)
class SystemChangeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/systemChange/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.systemChangeInstanceList.size() == 0
        assert model.systemChangeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.systemChangeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.systemChangeInstance != null
        assert view == '/systemChange/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/systemChange/show/1'
        assert controller.flash.message != null
        assert SystemChange.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/systemChange/list'

        populateValidParams(params)
        def systemChange = new SystemChange(params)

        assert systemChange.save() != null

        params.id = systemChange.id

        def model = controller.show()

        assert model.systemChangeInstance == systemChange
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/systemChange/list'

        populateValidParams(params)
        def systemChange = new SystemChange(params)

        assert systemChange.save() != null

        params.id = systemChange.id

        def model = controller.edit()

        assert model.systemChangeInstance == systemChange
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/systemChange/list'

        response.reset()

        populateValidParams(params)
        def systemChange = new SystemChange(params)

        assert systemChange.save() != null

        // test invalid parameters in update
        params.id = systemChange.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/systemChange/edit"
        assert model.systemChangeInstance != null

        systemChange.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/systemChange/show/$systemChange.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        systemChange.clearErrors()

        populateValidParams(params)
        params.id = systemChange.id
        params.version = -1
        controller.update()

        assert view == "/systemChange/edit"
        assert model.systemChangeInstance != null
        assert model.systemChangeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/systemChange/list'

        response.reset()

        populateValidParams(params)
        def systemChange = new SystemChange(params)

        assert systemChange.save() != null
        assert SystemChange.count() == 1

        params.id = systemChange.id

        controller.delete()

        assert SystemChange.count() == 0
        assert SystemChange.get(systemChange.id) == null
        assert response.redirectedUrl == '/systemChange/list'
    }
}
