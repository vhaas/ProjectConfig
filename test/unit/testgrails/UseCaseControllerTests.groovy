package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(UseCaseController)
@Mock(UseCase)
class UseCaseControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/useCase/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.useCaseInstanceList.size() == 0
        assert model.useCaseInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.useCaseInstance != null
    }

    void testSave() {
        controller.save()

        assert model.useCaseInstance != null
        assert view == '/useCase/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/useCase/show/1'
        assert controller.flash.message != null
        assert UseCase.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/useCase/list'

        populateValidParams(params)
        def useCase = new UseCase(params)

        assert useCase.save() != null

        params.id = useCase.id

        def model = controller.show()

        assert model.useCaseInstance == useCase
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/useCase/list'

        populateValidParams(params)
        def useCase = new UseCase(params)

        assert useCase.save() != null

        params.id = useCase.id

        def model = controller.edit()

        assert model.useCaseInstance == useCase
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/useCase/list'

        response.reset()

        populateValidParams(params)
        def useCase = new UseCase(params)

        assert useCase.save() != null

        // test invalid parameters in update
        params.id = useCase.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/useCase/edit"
        assert model.useCaseInstance != null

        useCase.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/useCase/show/$useCase.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        useCase.clearErrors()

        populateValidParams(params)
        params.id = useCase.id
        params.version = -1
        controller.update()

        assert view == "/useCase/edit"
        assert model.useCaseInstance != null
        assert model.useCaseInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/useCase/list'

        response.reset()

        populateValidParams(params)
        def useCase = new UseCase(params)

        assert useCase.save() != null
        assert UseCase.count() == 1

        params.id = useCase.id

        controller.delete()

        assert UseCase.count() == 0
        assert UseCase.get(useCase.id) == null
        assert response.redirectedUrl == '/useCase/list'
    }
}
