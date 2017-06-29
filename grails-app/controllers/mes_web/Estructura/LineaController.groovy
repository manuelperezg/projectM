package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class LineaController {

    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("linea_search_offset")){
			params.offset = session.getAttribute("linea_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("linea_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("linea_search_rows")){
			params.max = session.getAttribute("linea_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("linea_search_word")) params.offset = 0;
        } else if (session.getAttribute("linea_search_word")){
			params.search_word = session.getAttribute("linea_search_word");
        }
		//Recuperación de filtro
		if (params.filter_planta) {
			if (params.filter_planta != session.getAttribute("search_filter_planta")) params.offset = 0;
        } else if (session.getAttribute("search_filter_planta")){
			params.filter_planta = session.getAttribute("search_filter_planta");
        }
        
        def itemsList = Linea.createCriteria().list (params) {
			/*
            if ( params.search_word ) {
				or {
					ilike("nombre_1", "%${params.search_word}%")
					ilike("nombre_2", "%${params.search_word}%")
				}
            }
            */
            if (params.filter_planta && params.filter_planta != "ALL") {
                eq("planta.id", params.filter_planta)
            }
            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
            if ( !params.sort ){
				order("nombre","asc");
            }
        }

        session.setAttribute("linea_search_rows", params.search_rows);
        session.setAttribute("linea_search_word", params.search_word);
        session.setAttribute("linea_search_offset", params.offset);
        session.setAttribute("search_filter_planta", params.filter_planta);
		
		render(view:'index', model: [lineaList:itemsList, lineaCount: itemsList.totalCount,plantas:Planta.list(sort: "nombre", order: "asc")])
		
        //respond itemsList, model:[lineaCount: itemsList.totalCount], Planta.list()
    }

    def show(Linea linea) {
        
        
        if (params.offset){
				session.setAttribute("maquina_search_offset", offset);
		}
		if (!session.getAttribute("maquina_search_offset")){
			session.setAttribute("maquina_search_offset", 0);
		}
		
		def orderField = "nombre"
		if (params.sort) {
			orderField = params.sort
		}
		def orderMethod = "asc"
		if (params.order) {
			orderMethod = params.order
		}
        
        def itemsList = Maquina.createCriteria().list (max: 10, offset: session.getAttribute("maquina_search_offset")) {
            eq("linea.id", linea.id)
            order(orderField,orderMethod)
        }
		
        respond linea, model:[maquinaCount: itemsList.size(), maquinas: itemsList]
        
    }

    def create() {
		Linea linea = new Linea(params)
		if (springSecurityService.principal)
			linea.id_Autor = UserLG.findById(springSecurityService.principal.id);
		if (session.getAttribute("search_filter_planta") && session.getAttribute("search_filter_planta") != "ALL"){
			linea.planta = Planta.findById(session.getAttribute("search_filter_planta"));
		}
        respond linea
    }

    @Transactional
    def save(Linea linea) {
        if (linea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (linea.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond linea.errors, view:'create'
            return
        }

		// Recupera el id del autor
		if (springSecurityService.principal)
			linea.id_Autor = UserLG.findById(springSecurityService.principal.id);

        linea.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'linea.label', default: 'Linea'), linea.nombre])
                redirect linea
                //redirect action:"index", method:"GET"
            }
            '*' { respond linea, [status: CREATED] }
        }
    }

    def edit(Linea linea) {
        respond linea
    }

    @Transactional
    def update(Linea linea) {
        if (linea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (linea.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond linea.errors, view:'edit'
            return
        }

        linea.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'linea.label', default: 'Linea'), linea.nombre])
                redirect linea
                //redirect action:"index", method:"GET"
            }
            '*'{ respond linea, [status: OK] }
        }
    }

    @Transactional
    def delete(Linea linea) {

        if (linea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if (Maquina.findAll("from Maquina as items where items.linea.id = '"+linea.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'linea.label', default: 'Linea'), linea.nombre])
		}else{
			linea.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'linea.label', default: 'Linea'), linea.nombre])
		}
		
        request.withFormat {
            form multipartForm {
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Linea linea) {

        if (linea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if (Maquina.findAll("from Maquina as items where items.linea.id = '"+linea.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'linea.label', default: 'Linea'), linea.nombre])
		}else{
			linea.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'linea.label', default: 'Linea'), linea.nombre])
		}
		
        redirect action:"index", method:"GET"
    }
	
	def report(){
        String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
    }
    
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'linea.label', default: 'Linea'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
