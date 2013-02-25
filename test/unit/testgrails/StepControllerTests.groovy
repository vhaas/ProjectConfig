package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(StepController)
@Mock(Step)
class StepControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/step/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stepInstanceList.size() == 0
        assert model.stepInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stepInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stepInstance != null
        assert view == '/step/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/step/show/1'
        assert controller.flash.message != null
        assert Step.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/step/list'

        populateValidParams(params)
        def step = new Step(params)

        assert step.save() != null

        params.id = step.id

        def model = controller.show()

        assert model.stepInstance == step
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/step/list'

        populateValidParams(params)
        def step = new Step(params)

        assert step.save() != null

        params.id = step.id

        def model = controller.edit()

        assert model.stepInstance == step
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/step/list'

        response.reset()

        populateValidParams(params)
        def step = new Step(params)

        assert step.save() != null

        // test invalid parameters in update
        params.id = step.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/step/edit"
        assert model.stepInstance != null

        step.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/step/show/$step.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        step.clearErrors()

        populateValidParams(params)
        params.id = step.id
        params.version = -1
        controller.update()

        assert view == "/step/edit"
        assert model.stepInstance != null
        assert model.stepInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/step/list'

        response.reset()

        populateValidParams(params)
        def step = new Step(params)

        assert step.save() != null
        assert Step.count() == 1

        params.id = step.id

        controller.delete()

        assert Step.count() == 0
        assert Step.get(step.id) == null
        assert response.redirectedUrl == '/step/list'
    }
}
