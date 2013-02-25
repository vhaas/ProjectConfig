package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(UserStoryController)
@Mock(UserStory)
class UserStoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userStory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userStoryInstanceList.size() == 0
        assert model.userStoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userStoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userStoryInstance != null
        assert view == '/userStory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userStory/show/1'
        assert controller.flash.message != null
        assert UserStory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userStory/list'

        populateValidParams(params)
        def userStory = new UserStory(params)

        assert userStory.save() != null

        params.id = userStory.id

        def model = controller.show()

        assert model.userStoryInstance == userStory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userStory/list'

        populateValidParams(params)
        def userStory = new UserStory(params)

        assert userStory.save() != null

        params.id = userStory.id

        def model = controller.edit()

        assert model.userStoryInstance == userStory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userStory/list'

        response.reset()

        populateValidParams(params)
        def userStory = new UserStory(params)

        assert userStory.save() != null

        // test invalid parameters in update
        params.id = userStory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userStory/edit"
        assert model.userStoryInstance != null

        userStory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userStory/show/$userStory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userStory.clearErrors()

        populateValidParams(params)
        params.id = userStory.id
        params.version = -1
        controller.update()

        assert view == "/userStory/edit"
        assert model.userStoryInstance != null
        assert model.userStoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userStory/list'

        response.reset()

        populateValidParams(params)
        def userStory = new UserStory(params)

        assert userStory.save() != null
        assert UserStory.count() == 1

        params.id = userStory.id

        controller.delete()

        assert UserStory.count() == 0
        assert UserStory.get(userStory.id) == null
        assert response.redirectedUrl == '/userStory/list'
    }
}
