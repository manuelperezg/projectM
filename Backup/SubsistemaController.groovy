package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class SubsistemaController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("subsistema_search_offset")){
			params.offset = session.getAttribute("subsistema_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("subsistema_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("subsistema_search_rows")){
			params.max = session.getAttribute("subsistema_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("subsistema_search_word")) params.offset = 0;
        } else if (session.getAttribute("subsistema_search_word")){
			params.search_word = session.getAttribute("subsistema_search_word");
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
        def itemsList = Subsistema.createCriteria().list (params) {
			/*
            if ( params.search_word ) {
				or {
					ilike("nombre_1", "%${params.search_word}%")
					ilike("nombre_2", "%${params.search_word}%")
				}
            }
            */
            
            if ( params.maquina && params.maquina != "ALL") {
                eq("maquina.id", params.maquina)
            }
            
            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
        }
        
        session.setAttribute("subsistema_search_rows", params.search_rows);
        session.setAttribute("subsistema_search_word", params.search_word);
        session.setAttribute("subsistema_search_offset", params.offset);
        session.setAttribute("search_filter_maquina", params.filter_maquina);
        
        render(view:'index', model: [subsistemaList:itemsList, subsistemaCount: itemsList.totalCount, maquinas:Maquina.list(sort: "nombre", order: "asc")])
        //respond itemsList, model:[subsistemaCount: itemsList.totalCount]
    }

    def show(Subsistema subsistema) {
        respond subsistema
    }

    def create() {
		def subsistema = new Subsistema(params);
		
		if (springSecurityService.principal)
			subsistema.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
		if (session.getAttribute("search_filter_maquina") && session.getAttribute("search_filter_maquina") != "ALL"){
			subsistema.maquina = Maquina.findById(session.getAttribute("search_filter_maquina"));
		}
		
        respond subsistema
    }

    @Transactional
    def save(Subsistema subsistema) {
        if (subsistema == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (subsistema.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond subsistema.errors, view:'create'
            return
        }
        
        //Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				subsistema.archivo_Foto = "Subsistema_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+subsistema.archivo_Foto))
			}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		if (springSecurityService.principal)
			subsistema.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
        subsistema.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'subsistema.label', default: 'Subsistema'), subsistema.nombre])
                redirect subsistema
            }
            '*' { respond subsistema, [status: CREATED] }
        }
    }

    def edit(Subsistema subsistema) {
        respond subsistema
    }

    @Transactional
    def upchange(Subsistema subsistema) {
        if (subsistema == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (subsistema.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond subsistema.errors, view:'edit'
            return
        }
        
         //Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( grailsApplication.config.uploadFolder + "mes-icons/" + subsistema.archivo_Foto )
				if( file_real.exists() ){
					file_real.delete()
				}
				subsistema.archivo_Foto = "Subsistema_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+subsistema.archivo_Foto))
			}
		}


        subsistema.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'subsistema.label', default: 'Subsistema'), subsistema.nombre])
                redirect subsistema
            }
            '*'{ respond subsistema, [status: OK] }
        }
    }

    @Transactional
    def delete(Subsistema subsistema) {
		
        if (subsistema == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        subsistema.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'subsistema.label', default: 'Subsistema'), subsistema.nombre])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Subsistema subsistema) {
		
        if (subsistema == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        subsistema.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'subsistema.label', default: 'Subsistema'), subsistema.nombre])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'subsistema.label', default: 'Subsistema'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
