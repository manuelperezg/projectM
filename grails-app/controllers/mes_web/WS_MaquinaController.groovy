package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class WS_MaquinaController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("WS_Maquina_search_offset")){
			params.offset = session.getAttribute("WS_Maquina_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("WS_Maquina_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("WS_Maquina_search_rows")){
			params.max = session.getAttribute("WS_Maquina_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("WS_Maquina_search_word")) params.offset = 0;
        } else if (session.getAttribute("WS_Maquina_search_word")){
			params.search_word = session.getAttribute("WS_Maquina_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = WS_Maquina.createCriteria().list (params) {
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
        
        session.setAttribute("WS_Maquina_search_rows", params.search_rows);
        session.setAttribute("WS_Maquina_search_word", params.search_word);
        session.setAttribute("WS_Maquina_search_offset", params.offset);
        
        respond itemsList, model:[WS_MaquinaCount: itemsList.totalCount]
    }

    def show(WS_Maquina WS_Maquina) {
        respond WS_Maquina
    }

    def create() {
        respond new WS_Maquina(params)
    }

    @Transactional
    def save(WS_Maquina WS_Maquina) {
        if (WS_Maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_Maquina.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_Maquina.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			WS_Maquina.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
        WS_Maquina.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'WS_Maquina.label', default: 'WS_Maquina'), WS_Maquina.id])
                redirect WS_Maquina
            }
            '*' { respond WS_Maquina, [status: CREATED] }
        }
    }

    def edit(WS_Maquina WS_Maquina) {
        respond WS_Maquina
    }

    @Transactional
    def update(WS_Maquina WS_Maquina) {
        if (WS_Maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_Maquina.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_Maquina.errors, view:'edit'
            return
        }

        WS_Maquina.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'WS_Maquina.label', default: 'WS_Maquina'), WS_Maquina.id])
                redirect WS_Maquina
            }
            '*'{ respond WS_Maquina, [status: OK] }
        }
    }

    @Transactional
    def delete(WS_Maquina WS_Maquina) {
		
        if (WS_Maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        WS_Maquina.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_Maquina.label', default: 'WS_Maquina'), WS_Maquina.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(WS_Maquina WS_Maquina) {
		
        if (WS_Maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        WS_Maquina.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_Maquina.label', default: 'WS_Maquina'), WS_Maquina.id])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'WS_Maquina.label', default: 'WS_Maquina'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
