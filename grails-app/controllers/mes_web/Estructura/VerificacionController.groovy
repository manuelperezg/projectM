package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class VerificacionController {

    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def changeTab(Integer option){
		session.setAttribute("verificacion_tab", option);
	}
	
    def index(Integer max) {
		String titulo = "";
		if (params.title){
			session.setAttribute("title", params.title);
			titulo = params.title;
		}else{
			titulo = session.getAttribute("title");
		}
		
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("verificacion_search_offset")){
			params.offset = session.getAttribute("verificacion_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("verificacion_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("verificacion_search_rows")){
			params.max = session.getAttribute("verificacion_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("verificacion_search_word")) params.offset = 0;
        } else if (session.getAttribute("verificacion_search_word")){
			params.search_word = session.getAttribute("verificacion_search_word");
        }
        //Recuperación de filtro
		if (params.filter_maquina) {
			if (params.filter_maquina != session.getAttribute("search_filter_maquina")) params.offset = 0;
        } else if (session.getAttribute("search_filter_maquina")){
			params.filter_maquina = session.getAttribute("search_filter_maquina");
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = Verificacion.createCriteria().list (params) {
			/*
            if ( params.search_word ) {
				or {
					ilike("nombre_1", "%${params.search_word}%")
					ilike("nombre_2", "%${params.search_word}%")
				}
            }
            */
			
			if ( titulo != "" ){
				eq("claseVerificacion", titulo)
			}
			
            if ( params.filter_maquina && params.filter_maquina != "ALL") {
                eq("maquina.id", params.filter_maquina)
            }

            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
            if ( !params.sort ){
				order("nombre","asc");
            }
        }

        session.setAttribute("verificacion_search_rows", params.search_rows);
        session.setAttribute("verificacion_search_word", params.search_word);
        session.setAttribute("verificacion_search_offset", params.offset);
		session.setAttribute("search_filter_maquina", params.filter_maquina);
		
		render(view:'index', model: [verificacionList:itemsList, verificacionCount: itemsList.totalCount, maquinas:Maquina.list().sort{it.linea.planta.nombre+it.linea.nombre+it.nombre}]) 
		
        //respond itemsList, model:[verificacionCount: itemsList.totalCount, maquinas: Maquina.list()]
    }

    def show(Verificacion verificacion) {
		if (!session.getAttribute("verificacion_tab")){
			session.setAttribute("verificacion_tab", 1);
		}
		
		if (session.getAttribute("verificacion_tab") == 1){
			if (params.offset){
				session.setAttribute("punto_search_offset", offset);
			}
			if (!session.getAttribute("punto_search_offset")){
				session.setAttribute("punto_search_offset", 0);
			}
		} else if (session.getAttribute("verificacion_tab") == 2){
			if (params.offset){
				session.setAttribute("estandar_search_offset", offset);
			}
			if (!session.getAttribute("estandar_search_offset")){
				session.setAttribute("estandar_search_offset", 0);
			}
		}
		
		def orderField = "punto"
		if (params.sort) {
			orderField = params.sort
		}
		def orderMethod = "asc"
		if (params.order) {
			orderMethod = params.order
		}
		
		def puntosList = Punto.createCriteria().list (max: 10, offset: session.getAttribute("punto_search_offset")) {
            eq("verificacion.id", verificacion.id)
            if (session.getAttribute("verificacion_tab") == 1 || session.getAttribute("verificacion_tab") == 2 || session.getAttribute("verificacion_tab") == 3){
				order(orderField,orderMethod)
			}
        }
        
        orderField = "nombre"
		if (params.sort) {
			orderField = params.sort
		}
		
		def estandaresList = Estandar.createCriteria().list (max: 10, offset: session.getAttribute("estandar_search_offset")) {
            eq("verificacion.id", verificacion.id)
            if (session.getAttribute("verificacion_tab") == 4){
				order(orderField,orderMethod)
			}
        }
        
        respond verificacion, model:[puntoCount: puntosList.size(), puntos: puntosList, estandarCount: estandaresList.size(), estandares: estandaresList]
    }

    def create() {
		Verificacion verificacion = new Verificacion(params);
		verificacion.claseVerificacion = session.getAttribute("title");
		if (springSecurityService.principal)
			verificacion.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
		if (session.getAttribute("search_filter_maquina") && session.getAttribute("search_filter_maquina") != "ALL"){
			verificacion.maquina = Maquina.findById(session.getAttribute("search_filter_maquina"));
		}
        respond verificacion
    }

    @Transactional
    def save(Verificacion verificacion) {
        if (verificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (verificacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond verificacion.errors, view:'create'
            return
        }

		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		if (springSecurityService.principal)
			verificacion.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
		verificacion.claseVerificacion = session.getAttribute("title");
		
        verificacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'verificacion.label', default: 'Verificaci\u00f3n'), verificacion.nombre])
                redirect verificacion
                //redirect action:"index", method:"GET"
            }
            '*' { respond verificacion, [status: CREATED] }
        }
    }

    def edit(Verificacion verificacion) {
        respond verificacion
    }

    @Transactional
    def update(Verificacion verificacion) {
        if (verificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (verificacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond verificacion.errors, view:'edit'
            return
        }
        
        verificacion.claseVerificacion = session.getAttribute("title");

        verificacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'verificacion.label', default: 'Verificaci\u00f3n'), verificacion.nombre])
                redirect verificacion
                //redirect action:"index", method:"GET"
            }
            '*'{ respond verificacion, [status: OK] }
        }
    }

    @Transactional
    def delete(Verificacion verificacion) {

        if (verificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if (Punto.findAll("from Punto as items where items.verificacion.id = '"+verificacion.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'verificacion.label', default: 'Verificaci\u00f3n'), verificacion.nombre])
		}else{	
			//Punto.executeUpdate("DELETE FROM Puntos as p WHERE p.verificacion.id='"+verificacion.id.toString()+"'")
			verificacion.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'verificacion.label', default: 'Verificaci\u00f3n'), verificacion.nombre])
		}
		
        request.withFormat {
            form multipartForm {
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Verificacion verificacion) {

        if (verificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        if (Punto.findAll("from Punto as items where items.verificacion.id = '"+verificacion.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'verificacion.label', default: 'Verificaci\u00f3n'), verificacion.nombre])
		}else{	
			//Punto.executeUpdate("DELETE FROM Puntos as p WHERE p.verificacion.id='"+verificacion.id.toString()+"'")
			verificacion.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'verificacion.label', default: 'Verificaci\u00f3n'), verificacion.nombre])
		}

		redirect action:"index", method:"GET"

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'verificacion.label', default: 'Verificaci\u00f3n'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
