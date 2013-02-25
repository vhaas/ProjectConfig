package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(PreConditionsController)
@Mock(PreConditions)
class PreConditionsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/preConditions/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.preConditionsInstanceList.size() == 0
        assert model.preConditionsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.preConditionsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.preConditionsInstance != null
        assert view == '/preConditions/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/preConditions/show/1'
        assert controller.flash.message != null
        assert PreConditions.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/preConditions/list'

        populateValidParams(params)
        def preConditions = new PreConditions(params)

        assert preConditions.save() != null

        params.id = preConditions.id

        def model = controller.show()

        assert model.preConditionsInstance == preConditions
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/preConditions/list'

        populateValidParams(params)
        def preConditions = new PreConditions(params)

        assert preConditions.save() != null

        params.id = preConditions.id

        def model = controller.edit()

        assert model.preConditionsInstance == preConditions
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/preConditions/list'

        response.reset()

        populateValidParams(params)
        def preConditions = new PreConditions(params)

        assert preConditions.save() != null

        // test invalid parameters in update
        params.id = preConditions.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/preConditions/edit"
        assert model.preConditionsInstance != null

        preConditions.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/preConditions/show/$preConditions.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        preConditions.clearErrors()

        populateValidParams(params)
        params.id = preConditions.id
        params.version = -1
        controller.update()

        assert view == "/preConditions/edit"
        assert model.preConditionsInstance != null
        assert model.preConditionsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/preConditions/list'

        response.reset()

        populateValidParams(params)
        def preConditions = new PreConditions(params)

        assert preConditions.save() != null
        assert PreConditions.count() == 1

        params.id = preConditions.id

        controller.delete()

        assert PreConditions.count() == 0
        assert PreConditions.get(preConditions.id) == null
        assert response.redirectedUrl == '/preConditions/list'
    }
}
