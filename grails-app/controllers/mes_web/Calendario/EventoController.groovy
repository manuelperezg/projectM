package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class EventoController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("evento_search_offset")){
			params.offset = session.getAttribute("evento_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("evento_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("evento_search_rows")){
			params.max = session.getAttribute("evento_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("evento_search_word")) params.offset = 0;
        } else if (session.getAttribute("evento_search_word")){
			params.search_word = session.getAttribute("evento_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = Evento.createCriteria().list (params) {
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
        
        session.setAttribute("evento_search_rows", params.search_rows);
        session.setAttribute("evento_search_word", params.search_word);
        session.setAttribute("evento_search_offset", params.offset);
        
        respond itemsList, model:[eventoCount: itemsList.totalCount]
    }

    def show(Evento evento) {
        respond evento
    }

    def create() {
        respond new Evento(params)
    }

    @Transactional
    def save(Evento evento) {
        if (evento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (evento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond evento.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			evento.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
        evento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect evento
            }
            '*' { respond evento, [status: CREATED] }
        }
    }

    def edit(Evento evento) {
        respond evento
    }

    @Transactional
    def update(Evento evento) {
        if (evento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (evento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond evento.errors, view:'edit'
            return
        }

        evento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect evento
            }
            '*'{ respond evento, [status: OK] }
        }
    }

    @Transactional
    def delete(Evento evento) {
		
        if (evento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        evento.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Evento evento) {
		
        if (evento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        evento.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
