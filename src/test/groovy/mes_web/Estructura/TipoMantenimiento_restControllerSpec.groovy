package mes_web.Estructura

import grails.test.mixin.*
import spock.lang.*

@TestFor(TipoMantenimiento_restController)
@Mock(TipoMantenimiento_rest)
class TipoMantenimiento_restControllerSpec extends Specification {

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
            !model.tipoMantenimiento_restList
            model.tipoMantenimiento_restCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.tipoMantenimiento_rest!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def tipoMantenimiento_rest = new TipoMantenimiento_rest()
            tipoMantenimiento_rest.validate()
            controller.save(tipoMantenimiento_rest)

        then:"The create view is rendered again with the correct model"
            model.tipoMantenimiento_rest!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            tipoMantenimiento_rest = new TipoMantenimiento_rest(params)

            controller.save(tipoMantenimiento_rest)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/tipoMantenimiento_rest/show/1'
            controller.flash.message != null
            TipoMantenimiento_rest.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tipoMantenimiento_rest = new TipoMantenimiento_rest(params)
            controller.show(tipoMantenimiento_rest)

        then:"A model is populated containing the domain instance"
            model.tipoMantenimiento_rest == tipoMantenimiento_rest
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tipoMantenimiento_rest = new TipoMantenimiento_rest(params)
            controller.edit(tipoMantenimiento_rest)

        then:"A model is populated containing the domain instance"
            model.tipoMantenimiento_rest == tipoMantenimiento_rest
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/tipoMantenimiento_rest/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def tipoMantenimiento_rest = new TipoMantenimiento_rest()
            tipoMantenimiento_rest.validate()
            controller.update(tipoMantenimiento_rest)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.tipoMantenimiento_rest == tipoMantenimiento_rest

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            tipoMantenimiento_rest = new TipoMantenimiento_rest(params).save(flush: true)
            controller.update(tipoMantenimiento_rest)

        then:"A redirect is issued to the show action"
            tipoMantenimiento_rest != null
            response.redirectedUrl == "/tipoMantenimiento_rest/show/$tipoMantenimiento_rest.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/tipoMantenimiento_rest/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tipoMantenimiento_rest = new TipoMantenimiento_rest(params).save(flush: true)

        then:"It exists"
            TipoMantenimiento_rest.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(tipoMantenimiento_rest)

        then:"The instance is deleted"
            TipoMantenimiento_rest.count() == 0
            response.redirectedUrl == '/tipoMantenimiento_rest/index'
            flash.message != null
    }
}
