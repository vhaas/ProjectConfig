package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(RoadMapController)
@Mock(RoadMap)
class RoadMapControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/roadMap/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.roadMapInstanceList.size() == 0
        assert model.roadMapInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.roadMapInstance != null
    }

    void testSave() {
        controller.save()

        assert model.roadMapInstance != null
        assert view == '/roadMap/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/roadMap/show/1'
        assert controller.flash.message != null
        assert RoadMap.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/roadMap/list'

        populateValidParams(params)
        def roadMap = new RoadMap(params)

        assert roadMap.save() != null

        params.id = roadMap.id

        def model = controller.show()

        assert model.roadMapInstance == roadMap
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/roadMap/list'

        populateValidParams(params)
        def roadMap = new RoadMap(params)

        assert roadMap.save() != null

        params.id = roadMap.id

        def model = controller.edit()

        assert model.roadMapInstance == roadMap
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/roadMap/list'

        response.reset()

        populateValidParams(params)
        def roadMap = new RoadMap(params)

        assert roadMap.save() != null

        // test invalid parameters in update
        params.id = roadMap.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/roadMap/edit"
        assert model.roadMapInstance != null

        roadMap.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/roadMap/show/$roadMap.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        roadMap.clearErrors()

        populateValidParams(params)
        params.id = roadMap.id
        params.version = -1
        controller.update()

        assert view == "/roadMap/edit"
        assert model.roadMapInstance != null
        assert model.roadMapInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/roadMap/list'

        response.reset()

        populateValidParams(params)
        def roadMap = new RoadMap(params)

        assert roadMap.save() != null
        assert RoadMap.count() == 1

        params.id = roadMap.id

        controller.delete()

        assert RoadMap.count() == 0
        assert RoadMap.get(roadMap.id) == null
        assert response.redirectedUrl == '/roadMap/list'
    }
}
