package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(EffortEstimateController)
@Mock(EffortEstimate)
class EffortEstimateControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/effortEstimate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.effortEstimateInstanceList.size() == 0
        assert model.effortEstimateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.effortEstimateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.effortEstimateInstance != null
        assert view == '/effortEstimate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/effortEstimate/show/1'
        assert controller.flash.message != null
        assert EffortEstimate.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/effortEstimate/list'

        populateValidParams(params)
        def effortEstimate = new EffortEstimate(params)

        assert effortEstimate.save() != null

        params.id = effortEstimate.id

        def model = controller.show()

        assert model.effortEstimateInstance == effortEstimate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/effortEstimate/list'

        populateValidParams(params)
        def effortEstimate = new EffortEstimate(params)

        assert effortEstimate.save() != null

        params.id = effortEstimate.id

        def model = controller.edit()

        assert model.effortEstimateInstance == effortEstimate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/effortEstimate/list'

        response.reset()

        populateValidParams(params)
        def effortEstimate = new EffortEstimate(params)

        assert effortEstimate.save() != null

        // test invalid parameters in update
        params.id = effortEstimate.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/effortEstimate/edit"
        assert model.effortEstimateInstance != null

        effortEstimate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/effortEstimate/show/$effortEstimate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        effortEstimate.clearErrors()

        populateValidParams(params)
        params.id = effortEstimate.id
        params.version = -1
        controller.update()

        assert view == "/effortEstimate/edit"
        assert model.effortEstimateInstance != null
        assert model.effortEstimateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/effortEstimate/list'

        response.reset()

        populateValidParams(params)
        def effortEstimate = new EffortEstimate(params)

        assert effortEstimate.save() != null
        assert EffortEstimate.count() == 1

        params.id = effortEstimate.id

        controller.delete()

        assert EffortEstimate.count() == 0
        assert EffortEstimate.get(effortEstimate.id) == null
        assert response.redirectedUrl == '/effortEstimate/list'
    }
}
