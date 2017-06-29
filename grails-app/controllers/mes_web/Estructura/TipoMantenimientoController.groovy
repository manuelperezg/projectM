package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class TipoMantenimientoController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("tipoMantenimiento_search_offset")){
			params.offset = session.getAttribute("tipoMantenimiento_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("tipoMantenimiento_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("tipoMantenimiento_search_rows")){
			params.max = session.getAttribute("tipoMantenimiento_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("tipoMantenimiento_search_word")) params.offset = 0;
        } else if (session.getAttribute("tipoMantenimiento_search_word")){
			params.search_word = session.getAttribute("tipoMantenimiento_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = TipoMantenimiento.createCriteria().list (params) {
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
        
        session.setAttribute("tipoMantenimiento_search_rows", params.search_rows);
        session.setAttribute("tipoMantenimiento_search_word", params.search_word);
        session.setAttribute("tipoMantenimiento_search_offset", params.offset);
        
        respond itemsList, model:[tipoMantenimientoCount: itemsList.totalCount]
    }

    def show(TipoMantenimiento tipoMantenimiento) {
        respond tipoMantenimiento
    }

    def create() {
        respond new TipoMantenimiento(params)
    }

    @Transactional
    def save(TipoMantenimiento tipoMantenimiento) {
        if (tipoMantenimiento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoMantenimiento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoMantenimiento.errors, view:'create'
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
				tipoMantenimiento.archivo_Foto = "Mtto_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+tipoMantenimiento.archivo_Foto))
			}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		if (springSecurityService.principal)
			tipoMantenimiento.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
        tipoMantenimiento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoMantenimiento.label', default: 'TipoMantenimiento'), tipoMantenimiento.nombre])
                //redirect tipoMantenimiento
                redirect action:"index", method:"GET"
            }
            '*' { respond tipoMantenimiento, [status: CREATED] }
        }
    }

    def edit(TipoMantenimiento tipoMantenimiento) {
        respond tipoMantenimiento
    }

    @Transactional
    def upchange(TipoMantenimiento tipoMantenimiento) {
        if (tipoMantenimiento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoMantenimiento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoMantenimiento.errors, view:'edit'
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
				def file_real = new File( grailsApplication.config.uploadFolder + "mes-icons/" + tipoMantenimiento.archivo_Foto )
				if( file_real.exists() ){
					file_real.delete()
				}
				tipoMantenimiento.archivo_Foto = "Mtto_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+tipoMantenimiento.archivo_Foto))
			}
		}

        tipoMantenimiento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoMantenimiento.label', default: 'TipoMantenimiento'), tipoMantenimiento.nombre])
                //redirect tipoMantenimiento
                redirect action:"index", method:"GET"
            }
            '*'{ respond tipoMantenimiento, [status: OK] }
        }
    }

    @Transactional
    def delete(TipoMantenimiento tipoMantenimiento) {
		
        if (tipoMantenimiento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Borrando archivo anterior
		def directory = grailsApplication.config.uploadFolder + "mes-icons/";
		def file_real = new File( directory + tipoMantenimiento.archivo_Foto )
		if( file_real.exists() ){
			file_real.delete()
		}
				
        tipoMantenimiento.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoMantenimiento.label', default: 'TipoMantenimiento'), tipoMantenimiento.nombre])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(TipoMantenimiento tipoMantenimiento) {
		
        if (tipoMantenimiento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Borrando archivo anterior
		def directory = grailsApplication.config.uploadFolder + "mes-icons/";
		def file_real = new File( directory + tipoMantenimiento.archivo_Foto )
		if( file_real.exists() ){
			file_real.delete()
		}
		
        tipoMantenimiento.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoMantenimiento.label', default: 'TipoMantenimiento'), tipoMantenimiento.nombre])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoMantenimiento.label', default: 'TipoMantenimiento'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
