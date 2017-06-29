package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class PiezaController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("pieza_search_offset")){
			params.offset = session.getAttribute("pieza_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("pieza_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("pieza_search_rows")){
			params.max = session.getAttribute("pieza_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("pieza_search_word")) params.offset = 0;
        } else if (session.getAttribute("pieza_search_word")){
			params.search_word = session.getAttribute("pieza_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = Pieza.createCriteria().list (params) {
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
        
        session.setAttribute("pieza_search_rows", params.search_rows);
        session.setAttribute("pieza_search_word", params.search_word);
        session.setAttribute("pieza_search_offset", params.offset);
        
        respond itemsList, model:[piezaCount: itemsList.totalCount]
    }

    def show(Pieza pieza) {
        respond pieza
    }

    def create() {
        respond new Pieza(params)
    }

    @Transactional
    def save(Pieza pieza) {
        if (pieza == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pieza.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pieza.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			pieza.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
		//Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				pieza.archivo_Foto = "Pieza" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+pieza.archivo_Foto))
			}
		}

        pieza.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pieza.label', default: 'Pieza'), pieza.id])
                redirect pieza
            }
            '*' { respond pieza, [status: CREATED] }
        }
    }

    def edit(Pieza pieza) {
        respond pieza
    }

    @Transactional
    def upchange(Pieza pieza) {
        if (pieza == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pieza.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pieza.errors, view:'edit'
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
				pieza.archivo_Foto = "Pieza" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+pieza.archivo_Foto))
			}
		}
		
        pieza.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pieza.label', default: 'Pieza'), pieza.id])
                redirect pieza
            }
            '*'{ respond pieza, [status: OK] }
        }
    }

    @Transactional
    def delete(Pieza pieza) {
		
        if (pieza == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pieza.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pieza.label', default: 'Pieza'), pieza.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Pieza pieza) {
		
        if (pieza == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        pieza.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'pieza.label', default: 'Pieza'), pieza.id])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pieza.label', default: 'Pieza'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
