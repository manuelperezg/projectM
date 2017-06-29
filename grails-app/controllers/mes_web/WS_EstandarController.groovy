package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class WS_EstandarController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("WS_Estandar_search_offset")){
			params.offset = session.getAttribute("WS_Estandar_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("WS_Estandar_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("WS_Estandar_search_rows")){
			params.max = session.getAttribute("WS_Estandar_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("WS_Estandar_search_word")) params.offset = 0;
        } else if (session.getAttribute("WS_Estandar_search_word")){
			params.search_word = session.getAttribute("WS_Estandar_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = WS_Estandar.createCriteria().list (params) {
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
        
        session.setAttribute("WS_Estandar_search_rows", params.search_rows);
        session.setAttribute("WS_Estandar_search_word", params.search_word);
        session.setAttribute("WS_Estandar_search_offset", params.offset);
        
        respond itemsList, model:[WS_EstandarCount: itemsList.totalCount]
    }

    def show(WS_Estandar WS_Estandar) {
        respond WS_Estandar
    }

    def create() {
        respond new WS_Estandar(params)
    }

    @Transactional
    def save(WS_Estandar WS_Estandar) {
        if (WS_Estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_Estandar.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_Estandar.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			WS_Estandar.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
        WS_Estandar.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'WS_Estandar.label', default: 'WS_Estandar'), WS_Estandar.id])
                redirect WS_Estandar
            }
            '*' { respond WS_Estandar, [status: CREATED] }
        }
    }

    def edit(WS_Estandar WS_Estandar) {
        respond WS_Estandar
    }

    @Transactional
    def update(WS_Estandar WS_Estandar) {
        if (WS_Estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_Estandar.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_Estandar.errors, view:'edit'
            return
        }

        WS_Estandar.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'WS_Estandar.label', default: 'WS_Estandar'), WS_Estandar.id])
                redirect WS_Estandar
            }
            '*'{ respond WS_Estandar, [status: OK] }
        }
    }

    @Transactional
    def delete(WS_Estandar WS_Estandar) {
		
        if (WS_Estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        WS_Estandar.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_Estandar.label', default: 'WS_Estandar'), WS_Estandar.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(WS_Estandar WS_Estandar) {
		
        if (WS_Estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        WS_Estandar.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_Estandar.label', default: 'WS_Estandar'), WS_Estandar.id])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'WS_Estandar.label', default: 'WS_Estandar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
