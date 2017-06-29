package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class EstandarController {

    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("estandar_search_offset")){
			params.offset = session.getAttribute("estandar_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("estandar_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("estandar_search_rows")){
			params.max = session.getAttribute("estandar_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("estandar_search_word")) params.offset = 0;
        } else if (session.getAttribute("estandar_search_word")){
			params.search_word = session.getAttribute("estandar_search_word");
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = Estandar.createCriteria().list (params) {
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

        session.setAttribute("estandar_search_rows", params.search_rows);
        session.setAttribute("estandar_search_word", params.search_word);
        session.setAttribute("estandar_search_offset", params.offset);

        respond itemsList, model:[estandarCount: itemsList.totalCount]
    }

    def show(Estandar estandar) {
        respond estandar
    }

    def create() {
        Estandar estandar = new Estandar(params)
        if (springSecurityService.principal)
            estandar.id_Autor = UserLG.findById(springSecurityService.principal.id);
        respond estandar
    }

    @Transactional
    def save(Estandar estandar) {
        if (estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (estandar.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond estandar.errors, view:'create'
            return
        }

        // obtener id
        estandar.save flush:true

        //Recuperando el archivo
        def file = request.getFile('tmp-archivo')

        if (!file.empty) {
            if (file.getInputStream() != null){
                def directory       = grailsApplication.config.uploadFolder + "mes-files/";
                def fileName        = file.originalFilename;
                String name         = fileName.substring(0, fileName.lastIndexOf(".")-1)
                String extension    = fileName.substring(fileName.lastIndexOf("."));
                //def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
                estandar.archivo	= "Estandar_" + minutesName.toString() + extension ;

                file.transferTo(new File(directory+estandar.archivo))
            }
        }

        if (springSecurityService.principal)
            estandar.id_Autor = UserLG.findById(springSecurityService.principal.id)

        estandar.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'estandar.label', default: 'Est\u00e1ndar'), estandar.nombre])
                redirect action:'index'
            }
            '*' { respond estandar, [status: CREATED] }
        }
    }

    def edit(Estandar estandar) {
        respond estandar
    }

    @Transactional
    def upchange(Estandar estandar) {
        if (estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (estandar.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond estandar.errors, view:'edit'
            return
        }

        //Recuperando el archivo
        def file = request.getFile('tmp-archivo')

        if (!file.empty) {
            if (file.getInputStream() != null){
                def directory		= grailsApplication.config.uploadFolder + "mes-files/";
				def fileName        = file.originalFilename;
                String name         = fileName.substring(0, fileName.lastIndexOf(".")-1)
                String extension    = fileName.substring(fileName.lastIndexOf("."));
                //def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( directory + estandar.archivo );
				if( file_real.exists() ){
					file_real.delete()
				}
                estandar.archivo    = "Estandar_" + minutesName.toString() + extension ;

                file.transferTo(new File(directory+estandar.archivo))
            }
        }

        estandar.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'estandar.label', default: 'Est\u00e1ndar'), estandar.nombre])
                redirect estandar
            }
            '*'{ respond estandar, [status: OK] }
        }
    }

    @Transactional
    def delete(Estandar estandar) {

        if (estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Borrando archivo anexo
		def directory = grailsApplication.config.uploadFolder + "mes-files/";
		def file_real = new File( directory + estandar.archivo );
		if( file_real.exists() ){
			file_real.delete()
		}
        estandar.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'estandar.label', default: 'Est\u00e1ndar'), estandar.nombre])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Estandar estandar) {

        if (estandar == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Borrando archivo anexo
		def directory = grailsApplication.config.uploadFolder + "mes-files/";
		def file_real = new File( directory + estandar.archivo );
		if( file_real.exists() ){
			file_real.delete()
		}
        estandar.delete flush:true

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'estandar.label', default: 'Est\u00e1ndar'), estandar.nombre])
        redirect action:"index", method:"GET"

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'estandar.label', default: 'Est\u00e1ndar'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
