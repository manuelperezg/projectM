package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class WS_TipoVerificacionController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("WS_TipoVerificacion_search_offset")){
			params.offset = session.getAttribute("WS_TipoVerificacion_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("WS_TipoVerificacion_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("WS_TipoVerificacion_search_rows")){
			params.max = session.getAttribute("WS_TipoVerificacion_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("WS_TipoVerificacion_search_word")) params.offset = 0;
        } else if (session.getAttribute("WS_TipoVerificacion_search_word")){
			params.search_word = session.getAttribute("WS_TipoVerificacion_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = WS_TipoVerificacion.createCriteria().list (params) {
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
        }
        
        session.setAttribute("WS_TipoVerificacion_search_rows", params.search_rows);
        session.setAttribute("WS_TipoVerificacion_search_word", params.search_word);
        session.setAttribute("WS_TipoVerificacion_search_offset", params.offset);
        
        respond itemsList, model:[WS_TipoVerificacionCount: itemsList.totalCount]
    }

    def show(WS_TipoVerificacion WS_TipoVerificacion) {
        respond WS_TipoVerificacion
    }

    def create() {
        respond new WS_TipoVerificacion(params)
    }

    @Transactional
    def save(WS_TipoVerificacion WS_TipoVerificacion) {
        if (WS_TipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_TipoVerificacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_TipoVerificacion.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			WS_TipoVerificacion.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
        WS_TipoVerificacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'WS_TipoVerificacion.label', default: 'WS_TipoVerificacion'), WS_TipoVerificacion.id])
                redirect WS_TipoVerificacion
            }
            '*' { respond WS_TipoVerificacion, [status: CREATED] }
        }
    }

    def edit(WS_TipoVerificacion WS_TipoVerificacion) {
        respond WS_TipoVerificacion
    }

    @Transactional
    def update(WS_TipoVerificacion WS_TipoVerificacion) {
        if (WS_TipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_TipoVerificacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_TipoVerificacion.errors, view:'edit'
            return
        }

        WS_TipoVerificacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'WS_TipoVerificacion.label', default: 'WS_TipoVerificacion'), WS_TipoVerificacion.id])
                redirect WS_TipoVerificacion
            }
            '*'{ respond WS_TipoVerificacion, [status: OK] }
        }
    }

    @Transactional
    def delete(WS_TipoVerificacion WS_TipoVerificacion) {
		
        if (WS_TipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        WS_TipoVerificacion.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_TipoVerificacion.label', default: 'WS_TipoVerificacion'), WS_TipoVerificacion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(WS_TipoVerificacion WS_TipoVerificacion) {
		
        if (WS_TipoVerificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        WS_TipoVerificacion.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_TipoVerificacion.label', default: 'WS_TipoVerificacion'), WS_TipoVerificacion.id])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'WS_TipoVerificacion.label', default: 'WS_TipoVerificacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
