package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class PlantaController {
    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("planta_search_offset")){
			params.offset = session.getAttribute("planta_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("planta_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("planta_search_rows")){
			params.max = session.getAttribute("planta_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("planta_search_word")) params.offset = 0;
        } else if (session.getAttribute("planta_search_word")){
			params.search_word = session.getAttribute("planta_search_word");
        }
		
		params.sort = "nombre";
		
        def itemsList = Planta.createCriteria().list (params) {
            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
            if ( !params.sort ){
				order("nombre","asc");
            }
        }

        session.setAttribute("planta_search_rows", params.search_rows);
        session.setAttribute("planta_search_word", params.search_word);
        session.setAttribute("planta_search_offset", params.offset);

        respond itemsList, model:[plantaCount: itemsList.totalCount]
    }

    def show(Planta planta) {
        respond planta
    }

    def create() {
		Planta planta = new Planta(params)
		if (springSecurityService.principal)
			planta.id_Autor = UserLG.findById(springSecurityService.principal.id);
        respond planta
    }

    @Transactional
    def save(Planta planta) {
        if (planta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (planta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond planta.errors, view:'create'
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
				planta.archivo_Foto = "Planta_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+planta.archivo_Foto))
			}
		}
		
		//Recuperando el archivo
        file = request.getFile('tmp-archivo_Esquema')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				planta.archivo_Esquema = "Esquema_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+planta.archivo_Esquema))
			}
		}
		
		// Recupera el id del autor
		if (springSecurityService.principal)
			planta.id_Autor = UserLG.findById(springSecurityService.principal.id);

        planta.save flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'planta.label', default: 'Planta'), planta.nombre])
                //redirect planta
                redirect action:"index", method:"GET"
            }
            '*' { respond planta, [status: CREATED] }
        }
    }

    def edit(Planta planta) {
        respond planta
    }

    @Transactional
    def upchange(Planta planta) {
        if (planta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (planta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond planta.errors, view:'edit'
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
				def file_real = new File( directory + planta.archivo_Foto );
				if( file_real.exists() ){
					file_real.delete()
				}
				planta.archivo_Foto = "Planta_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+planta.archivo_Foto))
			}
		}
		
		//Recuperando el archivo
        file = request.getFile('tmp-archivo_Esquema')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( directory + planta.archivo_Esquema );
				if( file_real.exists() ){
					file_real.delete()
				}
				planta.archivo_Esquema = "Esquema_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+planta.archivo_Esquema))
			}
		}
		
        planta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'planta.label', default: 'Planta'), planta.nombre])
                //redirect planta
                redirect action:"index", method:"GET"
            }
            '*'{ respond planta, [status: OK] }
        }
    }

    @Transactional
    def delete(Planta planta) {

        if (planta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if (Linea.findAll("from Linea as items where items.planta.id = '"+planta.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'planta.label', default: 'Planta'), planta.nombre])
		}else{
			//Borrando archivo anexo 1
			def directory = grailsApplication.config.uploadFolder + "mes-icons/";
			def file_real = new File( directory + planta.archivo_Foto );
			if( file_real.exists() ){
				file_real.delete()
			}
			//Borrando archivo anexo 2
			file_real = new File( directory + planta.archivo_Foto );
			if( file_real.exists() ){
				file_real.delete()
			}
			
			planta.delete flush:true
			
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'planta.label', default: 'Planta'), planta.nombre])
		}
		
        request.withFormat {
            form multipartForm {
                
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Planta planta) {

        if (planta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if (Linea.findAll("from Linea as items where items.planta.id = '"+planta.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'planta.label', default: 'Planta'), planta.nombre])
		}else{
			//Borrando archivo anexo 1
			def directory = grailsApplication.config.uploadFolder + "mes-icons/";
			def file_real = new File( directory + planta.archivo_Foto );
			if( file_real.exists() ){
				file_real.delete()
			}
			//Borrando archivo anexo 2
			file_real = new File( directory + planta.archivo_Foto );
			if( file_real.exists() ){
				file_real.delete()
			}
			
			planta.delete flush:true

			flash.message = message(code: 'default.deleted.message', args: [message(code: 'planta.label', default: 'Planta'), planta.nombre])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'planta.label', default: 'Planta'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
