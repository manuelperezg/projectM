package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class SesionController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("sesion_search_offset")){
			params.offset = session.getAttribute("sesion_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("sesion_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("sesion_search_rows")){
			params.max = session.getAttribute("sesion_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("sesion_search_word")) params.offset = 0;
        } else if (session.getAttribute("sesion_search_word")){
			params.search_word = session.getAttribute("sesion_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = Sesion.createCriteria().list (params) {
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
        
        session.setAttribute("sesion_search_rows", params.search_rows);
        session.setAttribute("sesion_search_word", params.search_word);
        session.setAttribute("sesion_search_offset", params.offset);
        
        respond itemsList, model:[sesionCount: itemsList.totalCount]
    }

    def show(Sesion sesion) {
        respond sesion
    }

    def create() {
        respond new Sesion(params)
    }

    @Transactional
    def save(Sesion sesion) {
        if (sesion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sesion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sesion.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			sesion.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
        sesion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sesion.label', default: 'Sesion'), sesion.id])
                redirect sesion
            }
            '*' { respond sesion, [status: CREATED] }
        }
    }

    def edit(Sesion sesion) {
        respond sesion
    }

    @Transactional
    def update(Sesion sesion) {
        if (sesion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sesion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sesion.errors, view:'edit'
            return
        }

        sesion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sesion.label', default: 'Sesion'), sesion.id])
                redirect sesion
            }
            '*'{ respond sesion, [status: OK] }
        }
    }

    @Transactional
    def delete(Sesion sesion) {
		
        if (sesion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sesion.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sesion.label', default: 'Sesion'), sesion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Sesion sesion) {
		
        if (sesion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        sesion.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'sesion.label', default: 'Sesion'), sesion.id])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sesion.label', default: 'Sesion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
