package testgrails



import org.junit.*
import grails.test.mixin.*

@TestFor(ScenarioController)
@Mock(Scenario)
class ScenarioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/scenario/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.scenarioInstanceList.size() == 0
        assert model.scenarioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.scenarioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.scenarioInstance != null
        assert view == '/scenario/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/scenario/show/1'
        assert controller.flash.message != null
        assert Scenario.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/scenario/list'

        populateValidParams(params)
        def scenario = new Scenario(params)

        assert scenario.save() != null

        params.id = scenario.id

        def model = controller.show()

        assert model.scenarioInstance == scenario
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/scenario/list'

        populateValidParams(params)
        def scenario = new Scenario(params)

        assert scenario.save() != null

        params.id = scenario.id

        def model = controller.edit()

        assert model.scenarioInstance == scenario
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/scenario/list'

        response.reset()

        populateValidParams(params)
        def scenario = new Scenario(params)

        assert scenario.save() != null

        // test invalid parameters in update
        params.id = scenario.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/scenario/edit"
        assert model.scenarioInstance != null

        scenario.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/scenario/show/$scenario.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        scenario.clearErrors()

        populateValidParams(params)
        params.id = scenario.id
        params.version = -1
        controller.update()

        assert view == "/scenario/edit"
        assert model.scenarioInstance != null
        assert model.scenarioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/scenario/list'

        response.reset()

        populateValidParams(params)
        def scenario = new Scenario(params)

        assert scenario.save() != null
        assert Scenario.count() == 1

        params.id = scenario.id

        controller.delete()

        assert Scenario.count() == 0
        assert Scenario.get(scenario.id) == null
        assert response.redirectedUrl == '/scenario/list'
    }
}
