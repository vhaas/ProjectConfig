package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(ReleaseController)
@Mock(Release)
class ReleaseControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/release/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.releaseInstanceList.size() == 0
        assert model.releaseInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.releaseInstance != null
    }

    void testSave() {
        controller.save()

        assert model.releaseInstance != null
        assert view == '/release/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/release/show/1'
        assert controller.flash.message != null
        assert Release.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/release/list'

        populateValidParams(params)
        def release = new Release(params)

        assert release.save() != null

        params.id = release.id

        def model = controller.show()

        assert model.releaseInstance == release
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/release/list'

        populateValidParams(params)
        def release = new Release(params)

        assert release.save() != null

        params.id = release.id

        def model = controller.edit()

        assert model.releaseInstance == release
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/release/list'

        response.reset()

        populateValidParams(params)
        def release = new Release(params)

        assert release.save() != null

        // test invalid parameters in update
        params.id = release.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/release/edit"
        assert model.releaseInstance != null

        release.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/release/show/$release.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        release.clearErrors()

        populateValidParams(params)
        params.id = release.id
        params.version = -1
        controller.update()

        assert view == "/release/edit"
        assert model.releaseInstance != null
        assert model.releaseInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/release/list'

        response.reset()

        populateValidParams(params)
        def release = new Release(params)

        assert release.save() != null
        assert Release.count() == 1

        params.id = release.id

        controller.delete()

        assert Release.count() == 0
        assert Release.get(release.id) == null
        assert response.redirectedUrl == '/release/list'
    }
}
