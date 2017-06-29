package mes_web.Estructura

import grails.test.mixin.*
import spock.lang.*

@TestFor(Planta_restController)
@Mock(Planta_rest)
class Planta_restControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.planta_restList
            model.planta_restCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.planta_rest!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def planta_rest = new Planta_rest()
            planta_rest.validate()
            controller.save(planta_rest)

        then:"The create view is rendered again with the correct model"
            model.planta_rest!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            planta_rest = new Planta_rest(params)

            controller.save(planta_rest)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/planta_rest/show/1'
            controller.flash.message != null
            Planta_rest.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def planta_rest = new Planta_rest(params)
            controller.show(planta_rest)

        then:"A model is populated containing the domain instance"
            model.planta_rest == planta_rest
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def planta_rest = new Planta_rest(params)
            controller.edit(planta_rest)

        then:"A model is populated containing the domain instance"
            model.planta_rest == planta_rest
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/planta_rest/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def planta_rest = new Planta_rest()
            planta_rest.validate()
            controller.update(planta_rest)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.planta_rest == planta_rest

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            planta_rest = new Planta_rest(params).save(flush: true)
            controller.update(planta_rest)

        then:"A redirect is issued to the show action"
            planta_rest != null
            response.redirectedUrl == "/planta_rest/show/$planta_rest.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/planta_rest/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def planta_rest = new Planta_rest(params).save(flush: true)

        then:"It exists"
            Planta_rest.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(planta_rest)

        then:"The instance is deleted"
            Planta_rest.count() == 0
            response.redirectedUrl == '/planta_rest/index'
            flash.message != null
    }
}
