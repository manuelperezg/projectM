package mes_web

import grails.test.mixin.*
import spock.lang.*

@TestFor(WS_VerificacionController)
@Mock(WS_Verificacion)
class WS_VerificacionControllerSpec extends Specification {

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
            !model.WS_VerificacionList
            model.WS_VerificacionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.WS_Verificacion!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def WS_Verificacion = new WS_Verificacion()
            WS_Verificacion.validate()
            controller.save(WS_Verificacion)

        then:"The create view is rendered again with the correct model"
            model.WS_Verificacion!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            WS_Verificacion = new WS_Verificacion(params)

            controller.save(WS_Verificacion)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/WS_Verificacion/show/1'
            controller.flash.message != null
            WS_Verificacion.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def WS_Verificacion = new WS_Verificacion(params)
            controller.show(WS_Verificacion)

        then:"A model is populated containing the domain instance"
            model.WS_Verificacion == WS_Verificacion
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def WS_Verificacion = new WS_Verificacion(params)
            controller.edit(WS_Verificacion)

        then:"A model is populated containing the domain instance"
            model.WS_Verificacion == WS_Verificacion
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/WS_Verificacion/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def WS_Verificacion = new WS_Verificacion()
            WS_Verificacion.validate()
            controller.update(WS_Verificacion)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.WS_Verificacion == WS_Verificacion

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            WS_Verificacion = new WS_Verificacion(params).save(flush: true)
            controller.update(WS_Verificacion)

        then:"A redirect is issued to the show action"
            WS_Verificacion != null
            response.redirectedUrl == "/WS_Verificacion/show/$WS_Verificacion.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/WS_Verificacion/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def WS_Verificacion = new WS_Verificacion(params).save(flush: true)

        then:"It exists"
            WS_Verificacion.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(WS_Verificacion)

        then:"The instance is deleted"
            WS_Verificacion.count() == 0
            response.redirectedUrl == '/WS_Verificacion/index'
            flash.message != null
    }
}
