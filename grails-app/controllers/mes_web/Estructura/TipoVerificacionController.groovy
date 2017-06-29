package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class TipoVerificacionController {

    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("tipoVerificacion_search_offset")){
			params.offset = session.getAttribute("tipoVerificacion_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("tipoVerificacion_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("tipoVerificacion_search_rows")){
			params.max = session.getAttribute("tipoVerificacion_search_rows").toInteger();
        } else {
			params.max = 10;
        }
        params.search_rows = params.max;
        //Recuperando la palabra de busqueda
        if (params.search_clean == "1") {
			params.search_word = "";
			params.offset = 0;
		} else if (params.search_word){
			//Enviada la palabra a buscar
			if (params.search_word != session.getAttribute("tipoVerificacion_search_word")) params.offset = 0;
        } else if (session.getAttribute("tipoVerificacion_search_word")){
			params.search_word = session.getAttribute("tipoVerificacion_search_word");
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = TipoVerificacion.createCriteria().list (params) {
			/*
            if ( params.search_word ) {
				or {
					ilike("nombre_1", "%${params.search_word}%")
					ilike("nombre_2", "%${params.search_word}%")
				}
            }
            */
            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
            if ( !params.sort ){
				order("nombre","asc");
            }
        }

        session.setAttribute("tipoVerificacion_search_rows", params.search_rows);
        session.setAttribute("tipoVerificacion_search_word", params.search_word);
        session.setAttribute("tipoVerificacion_search_offset", params.offset);

        respond itemsList, model:[tipoVerificacionCount: itemsList.totalCount]
    }

    def show(TipoVerificacion tipoVerificacion) {
        respond tipoVerificacion
    }

    def create() {
        respond new TipoVerificacion(params)
    }

    @Transactional
    def save(TipoVerificacion tipoVerificacion) {
        if (tipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoVerificacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoVerificacion.errors, view:'create'
            return
        }

		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna

		if (springSecurityService.principal)
			tipoVerificacion.id_Autor = UserLG.findById(springSecurityService.principal.id);


        tipoVerificacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoVerificacion.label', default: 'TipoVerificacion'), tipoVerificacion.nombre])
                redirect tipoVerificacion
            }
            '*' { respond tipoVerificacion, [status: CREATED] }
        }
    }

    def edit(TipoVerificacion tipoVerificacion) {
        respond tipoVerificacion
    }

    @Transactional
    def update(TipoVerificacion tipoVerificacion) {
        if (tipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoVerificacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoVerificacion.errors, view:'edit'
            return
        }

        tipoVerificacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoVerificacion.label', default: 'TipoVerificacion'), tipoVerificacion.nombre])
                redirect tipoVerificacion
            }
            '*'{ respond tipoVerificacion, [status: OK] }
        }
    }

    @Transactional
    def delete(TipoVerificacion tipoVerificacion) {

        if (tipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tipoVerificacion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoVerificacion.label', default: 'TipoVerificacion'), tipoVerificacion.nombre])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(TipoVerificacion tipoVerificacion) {

        if (tipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tipoVerificacion.delete flush:true

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoVerificacion.label', default: 'TipoVerificacion'), tipoVerificacion.nombre])
        redirect action:"index", method:"GET"

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoVerificacion.label', default: 'TipoVerificacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
