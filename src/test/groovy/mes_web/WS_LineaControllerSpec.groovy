package mes_web

import grails.test.mixin.*
import spock.lang.*

@TestFor(WS_LineaController)
@Mock(WS_Linea)
class WS_LineaControllerSpec extends Specification {

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
            !model.WS_LineaList
            model.WS_LineaCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.WS_Linea!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def WS_Linea = new WS_Linea()
            WS_Linea.validate()
            controller.save(WS_Linea)

        then:"The create view is rendered again with the correct model"
            model.WS_Linea!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            WS_Linea = new WS_Linea(params)

            controller.save(WS_Linea)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/WS_Linea/show/1'
            controller.flash.message != null
            WS_Linea.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def WS_Linea = new WS_Linea(params)
            controller.show(WS_Linea)

        then:"A model is populated containing the domain instance"
            model.WS_Linea == WS_Linea
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def WS_Linea = new WS_Linea(params)
            controller.edit(WS_Linea)

        then:"A model is populated containing the domain instance"
            model.WS_Linea == WS_Linea
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/WS_Linea/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def WS_Linea = new WS_Linea()
            WS_Linea.validate()
            controller.update(WS_Linea)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.WS_Linea == WS_Linea

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            WS_Linea = new WS_Linea(params).save(flush: true)
            controller.update(WS_Linea)

        then:"A redirect is issued to the show action"
            WS_Linea != null
            response.redirectedUrl == "/WS_Linea/show/$WS_Linea.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/WS_Linea/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def WS_Linea = new WS_Linea(params).save(flush: true)

        then:"It exists"
            WS_Linea.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(WS_Linea)

        then:"The instance is deleted"
            WS_Linea.count() == 0
            response.redirectedUrl == '/WS_Linea/index'
            flash.message != null
    }
}
