package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class WS_CategoriaController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("WS_Categoria_search_offset")){
			params.offset = session.getAttribute("WS_Categoria_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("WS_Categoria_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("WS_Categoria_search_rows")){
			params.max = session.getAttribute("WS_Categoria_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("WS_Categoria_search_word")) params.offset = 0;
        } else if (session.getAttribute("WS_Categoria_search_word")){
			params.search_word = session.getAttribute("WS_Categoria_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = WS_Categoria.createCriteria().list (params) {
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
        
        session.setAttribute("WS_Categoria_search_rows", params.search_rows);
        session.setAttribute("WS_Categoria_search_word", params.search_word);
        session.setAttribute("WS_Categoria_search_offset", params.offset);
        
        respond itemsList, model:[WS_CategoriaCount: itemsList.totalCount]
    }

    def show(WS_Categoria WS_Categoria) {
        respond WS_Categoria
    }

    def create() {
        respond new WS_Categoria(params)
    }

    @Transactional
    def save(WS_Categoria WS_Categoria) {
        if (WS_Categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_Categoria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_Categoria.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			WS_Categoria.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
        WS_Categoria.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'WS_Categoria.label', default: 'WS_Categoria'), WS_Categoria.id])
                redirect WS_Categoria
            }
            '*' { respond WS_Categoria, [status: CREATED] }
        }
    }

    def edit(WS_Categoria WS_Categoria) {
        respond WS_Categoria
    }

    @Transactional
    def update(WS_Categoria WS_Categoria) {
        if (WS_Categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (WS_Categoria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond WS_Categoria.errors, view:'edit'
            return
        }

        WS_Categoria.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'WS_Categoria.label', default: 'WS_Categoria'), WS_Categoria.id])
                redirect WS_Categoria
            }
            '*'{ respond WS_Categoria, [status: OK] }
        }
    }

    @Transactional
    def delete(WS_Categoria WS_Categoria) {
		
        if (WS_Categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        WS_Categoria.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_Categoria.label', default: 'WS_Categoria'), WS_Categoria.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(WS_Categoria WS_Categoria) {
		
        if (WS_Categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        WS_Categoria.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'WS_Categoria.label', default: 'WS_Categoria'), WS_Categoria.id])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'WS_Categoria.label', default: 'WS_Categoria'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
