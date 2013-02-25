package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(FirstEffortEstimateController)
@Mock(FirstEffortEstimate)
class FirstEffortEstimateControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/firstEffortEstimate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.firstEffortEstimateInstanceList.size() == 0
        assert model.firstEffortEstimateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.firstEffortEstimateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.firstEffortEstimateInstance != null
        assert view == '/firstEffortEstimate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/firstEffortEstimate/show/1'
        assert controller.flash.message != null
        assert FirstEffortEstimate.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/firstEffortEstimate/list'

        populateValidParams(params)
        def firstEffortEstimate = new FirstEffortEstimate(params)

        assert firstEffortEstimate.save() != null

        params.id = firstEffortEstimate.id

        def model = controller.show()

        assert model.firstEffortEstimateInstance == firstEffortEstimate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/firstEffortEstimate/list'

        populateValidParams(params)
        def firstEffortEstimate = new FirstEffortEstimate(params)

        assert firstEffortEstimate.save() != null

        params.id = firstEffortEstimate.id

        def model = controller.edit()

        assert model.firstEffortEstimateInstance == firstEffortEstimate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/firstEffortEstimate/list'

        response.reset()

        populateValidParams(params)
        def firstEffortEstimate = new FirstEffortEstimate(params)

        assert firstEffortEstimate.save() != null

        // test invalid parameters in update
        params.id = firstEffortEstimate.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/firstEffortEstimate/edit"
        assert model.firstEffortEstimateInstance != null

        firstEffortEstimate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/firstEffortEstimate/show/$firstEffortEstimate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        firstEffortEstimate.clearErrors()

        populateValidParams(params)
        params.id = firstEffortEstimate.id
        params.version = -1
        controller.update()

        assert view == "/firstEffortEstimate/edit"
        assert model.firstEffortEstimateInstance != null
        assert model.firstEffortEstimateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/firstEffortEstimate/list'

        response.reset()

        populateValidParams(params)
        def firstEffortEstimate = new FirstEffortEstimate(params)

        assert firstEffortEstimate.save() != null
        assert FirstEffortEstimate.count() == 1

        params.id = firstEffortEstimate.id

        controller.delete()

        assert FirstEffortEstimate.count() == 0
        assert FirstEffortEstimate.get(firstEffortEstimate.id) == null
        assert response.redirectedUrl == '/firstEffortEstimate/list'
    }
}
