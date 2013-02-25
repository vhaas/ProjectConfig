package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(PlannedEffortController)
@Mock(PlannedEffort)
class PlannedEffortControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/plannedEffort/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.plannedEffortInstanceList.size() == 0
        assert model.plannedEffortInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.plannedEffortInstance != null
    }

    void testSave() {
        controller.save()

        assert model.plannedEffortInstance != null
        assert view == '/plannedEffort/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/plannedEffort/show/1'
        assert controller.flash.message != null
        assert PlannedEffort.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/plannedEffort/list'

        populateValidParams(params)
        def plannedEffort = new PlannedEffort(params)

        assert plannedEffort.save() != null

        params.id = plannedEffort.id

        def model = controller.show()

        assert model.plannedEffortInstance == plannedEffort
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/plannedEffort/list'

        populateValidParams(params)
        def plannedEffort = new PlannedEffort(params)

        assert plannedEffort.save() != null

        params.id = plannedEffort.id

        def model = controller.edit()

        assert model.plannedEffortInstance == plannedEffort
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/plannedEffort/list'

        response.reset()

        populateValidParams(params)
        def plannedEffort = new PlannedEffort(params)

        assert plannedEffort.save() != null

        // test invalid parameters in update
        params.id = plannedEffort.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/plannedEffort/edit"
        assert model.plannedEffortInstance != null

        plannedEffort.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/plannedEffort/show/$plannedEffort.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        plannedEffort.clearErrors()

        populateValidParams(params)
        params.id = plannedEffort.id
        params.version = -1
        controller.update()

        assert view == "/plannedEffort/edit"
        assert model.plannedEffortInstance != null
        assert model.plannedEffortInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/plannedEffort/list'

        response.reset()

        populateValidParams(params)
        def plannedEffort = new PlannedEffort(params)

        assert plannedEffort.save() != null
        assert PlannedEffort.count() == 1

        params.id = plannedEffort.id

        controller.delete()

        assert PlannedEffort.count() == 0
        assert PlannedEffort.get(plannedEffort.id) == null
        assert response.redirectedUrl == '/plannedEffort/list'
    }
}
