package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(IterationController)
@Mock(Iteration)
class IterationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/iteration/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.iterationInstanceList.size() == 0
        assert model.iterationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.iterationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.iterationInstance != null
        assert view == '/iteration/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/iteration/show/1'
        assert controller.flash.message != null
        assert Iteration.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/iteration/list'

        populateValidParams(params)
        def iteration = new Iteration(params)

        assert iteration.save() != null

        params.id = iteration.id

        def model = controller.show()

        assert model.iterationInstance == iteration
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/iteration/list'

        populateValidParams(params)
        def iteration = new Iteration(params)

        assert iteration.save() != null

        params.id = iteration.id

        def model = controller.edit()

        assert model.iterationInstance == iteration
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/iteration/list'

        response.reset()

        populateValidParams(params)
        def iteration = new Iteration(params)

        assert iteration.save() != null

        // test invalid parameters in update
        params.id = iteration.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/iteration/edit"
        assert model.iterationInstance != null

        iteration.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/iteration/show/$iteration.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        iteration.clearErrors()

        populateValidParams(params)
        params.id = iteration.id
        params.version = -1
        controller.update()

        assert view == "/iteration/edit"
        assert model.iterationInstance != null
        assert model.iterationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/iteration/list'

        response.reset()

        populateValidParams(params)
        def iteration = new Iteration(params)

        assert iteration.save() != null
        assert Iteration.count() == 1

        params.id = iteration.id

        controller.delete()

        assert Iteration.count() == 0
        assert Iteration.get(iteration.id) == null
        assert response.redirectedUrl == '/iteration/list'
    }
}
