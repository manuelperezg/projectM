package mes_web.Estructura

import grails.test.mixin.*
import spock.lang.*

@TestFor(TipoVerificacionController)
@Mock(TipoVerificacion)
class TipoVerificacionControllerSpec extends Specification {

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
            !model.tipoVerificacionList
            model.tipoVerificacionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.tipoVerificacion!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def tipoVerificacion = new TipoVerificacion()
            tipoVerificacion.validate()
            controller.save(tipoVerificacion)

        then:"The create view is rendered again with the correct model"
            model.tipoVerificacion!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            tipoVerificacion = new TipoVerificacion(params)

            controller.save(tipoVerificacion)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/tipoVerificacion/show/1'
            controller.flash.message != null
            TipoVerificacion.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tipoVerificacion = new TipoVerificacion(params)
            controller.show(tipoVerificacion)

        then:"A model is populated containing the domain instance"
            model.tipoVerificacion == tipoVerificacion
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tipoVerificacion = new TipoVerificacion(params)
            controller.edit(tipoVerificacion)

        then:"A model is populated containing the domain instance"
            model.tipoVerificacion == tipoVerificacion
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/tipoVerificacion/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def tipoVerificacion = new TipoVerificacion()
            tipoVerificacion.validate()
            controller.update(tipoVerificacion)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.tipoVerificacion == tipoVerificacion

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            tipoVerificacion = new TipoVerificacion(params).save(flush: true)
            controller.update(tipoVerificacion)

        then:"A redirect is issued to the show action"
            tipoVerificacion != null
            response.redirectedUrl == "/tipoVerificacion/show/$tipoVerificacion.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/tipoVerificacion/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tipoVerificacion = new TipoVerificacion(params).save(flush: true)

        then:"It exists"
            TipoVerificacion.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(tipoVerificacion)

        then:"The instance is deleted"
            TipoVerificacion.count() == 0
            response.redirectedUrl == '/tipoVerificacion/index'
            flash.message != null
    }
}
