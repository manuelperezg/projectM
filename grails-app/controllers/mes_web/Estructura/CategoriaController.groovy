package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class CategoriaController {

    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("categoria_search_offset")){
			params.offset = session.getAttribute("categoria_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("categoria_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("categoria_search_rows")){
			params.max = session.getAttribute("categoria_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("categoria_search_word")) params.offset = 0;
        } else if (session.getAttribute("categoria_search_word")){
			params.search_word = session.getAttribute("categoria_search_word");
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        def itemsList = Categoria.createCriteria().list (params) {
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

        session.setAttribute("categoria_search_rows", params.search_rows);
        session.setAttribute("categoria_search_word", params.search_word);
        session.setAttribute("categoria_search_offset", params.offset);

        respond itemsList, model:[categoriaCount: itemsList.totalCount]
    }

    def show(Categoria categoria) {
        respond categoria
    }

    def create() {
        respond new Categoria(params)
    }

    @Transactional
    def save(Categoria categoria) {
        if (categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (categoria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond categoria.errors, view:'create'
            return
        }

		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna

		if (springSecurityService.principal)
			categoria.id_Autor = UserLG.findById(springSecurityService.principal.id);

        categoria.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.nombre])
                redirect action:"index", method:"GET"
            }
            '*' { respond categoria, [status: CREATED] }
        }
    }

    def edit(Categoria categoria) {
        respond categoria
    }

    @Transactional
    def update(Categoria categoria) {
        if (categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (categoria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond categoria.errors, view:'edit'
            return
        }

        categoria.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.nombre])
                redirect categoria
            }
            '*'{ respond categoria, [status: OK] }
        }
    }

    @Transactional
    def delete(Categoria categoria) {

        if (categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Detectar los materiales que tienen la categoria
		if (Material.findAll("from Material as items inner join items.categoria cat where cat.id = '"+categoria.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.nombre])
		}else{
			categoria.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.nombre])
		}

        request.withFormat {
            form multipartForm {
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Categoria categoria) {

        if (categoria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		//Detectar los materiales que tienen la categoria
		if (Material.findAll("from Material as items inner join items.categoria cat where cat.id = '"+categoria.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.nombre])
		}else{
			categoria.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoria.nombre])
		}
		
        redirect action:"index", method:"GET"

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
