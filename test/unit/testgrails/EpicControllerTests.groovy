package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(EpicController)
@Mock(Epic)
class EpicControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/epic/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.epicInstanceList.size() == 0
        assert model.epicInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.epicInstance != null
    }

    void testSave() {
        controller.save()

        assert model.epicInstance != null
        assert view == '/epic/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/epic/show/1'
        assert controller.flash.message != null
        assert Epic.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/epic/list'

        populateValidParams(params)
        def epic = new Epic(params)

        assert epic.save() != null

        params.id = epic.id

        def model = controller.show()

        assert model.epicInstance == epic
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/epic/list'

        populateValidParams(params)
        def epic = new Epic(params)

        assert epic.save() != null

        params.id = epic.id

        def model = controller.edit()

        assert model.epicInstance == epic
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/epic/list'

        response.reset()

        populateValidParams(params)
        def epic = new Epic(params)

        assert epic.save() != null

        // test invalid parameters in update
        params.id = epic.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/epic/edit"
        assert model.epicInstance != null

        epic.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/epic/show/$epic.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        epic.clearErrors()

        populateValidParams(params)
        params.id = epic.id
        params.version = -1
        controller.update()

        assert view == "/epic/edit"
        assert model.epicInstance != null
        assert model.epicInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/epic/list'

        response.reset()

        populateValidParams(params)
        def epic = new Epic(params)

        assert epic.save() != null
        assert Epic.count() == 1

        params.id = epic.id

        controller.delete()

        assert Epic.count() == 0
        assert Epic.get(epic.id) == null
        assert response.redirectedUrl == '/epic/list'
    }
}
