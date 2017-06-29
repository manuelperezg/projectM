package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class PuntoController {

    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("punto_search_offset")){
			params.offset = session.getAttribute("punto_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("punto_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("punto_search_rows")){
			params.max = session.getAttribute("punto_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("punto_search_word")) params.offset = 0;
        } else if (session.getAttribute("punto_search_word")){
			params.search_word = session.getAttribute("punto_search_word");
        }
        //Recuperación de filtro
		if (params.filter_verificacion) {
			if (params.filter_verificacion != session.getAttribute("search_filter_verificacion")) params.offset = 0;
        } else if (session.getAttribute("search_filter_verificacion")){
			params.filter_verificacion = session.getAttribute("search_filter_verificacion");
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = Punto.createCriteria().list (params) {
            if ( params.verificacion ) {
                eq("verificacion.id", params.verificacion)
            }

            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
        }

        session.setAttribute("punto_search_rows", params.search_rows);
        session.setAttribute("punto_search_word", params.search_word);
        session.setAttribute("punto_search_offset", params.offset);
		session.setAttribute("search_filter_verificacion", params.filter_verificacion);

		render(view:'index', model: [puntoList:itemsList, puntoCount: itemsList.totalCount,verificaciones:Verificacion.list(sort: "nombre", order: "asc")])

        //respond itemsList, model:[puntoCount: itemsList.totalCount, verificacion:Verificacion.list(sort: "nombre", order: "asc")]
    }

    def show(Punto punto) {
        respond punto
    }

    def create() {
		Punto punto = new Punto(params);

		if ( params.verificacion ) {
			punto.verificacion = Verificacion.findById(params.verificacion);
		}

		if (springSecurityService.principal)
			punto.id_Autor = UserLG.findById(springSecurityService.principal.id);

		/*
		if (session.getAttribute("search_filter_planta") && session.getAttribute("search_filter_verificacion") != "ALL"){
			punto.verificacion = Verificacion.findById(session.getAttribute("search_filter_verificacion"));
		}
		*/

        respond punto, model:[materialesList:Material.list()]
    }

    @Transactional
    def save(Punto punto) {
        if (punto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (punto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond punto.errors, view:'create'
            return
        }

		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		if (springSecurityService.principal)
			punto.id_Autor = UserLG.findById(springSecurityService.principal.id);

		def maxPunto = Punto.createCriteria().get {
            eq("verificacion.id", punto.verificacion.id)
            projections {
                max "punto"
            }
        }

        if (maxPunto) {
            punto.punto = maxPunto + 1
        } else {
           punto.punto = 1
        }

		//Recuperando el archivo
        def file = request.getFile('tmp-foto_malo')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				punto.foto_malo		= "PuntoMalo_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+punto.foto_malo))
			}
		}

		//Recuperando el archivo
        file = request.getFile('tmp-foto_bueno')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				punto.foto_bueno	= "PuntoBueno_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+punto.foto_bueno))
			}
		}

        punto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'punto.label', default: 'Punto'), punto.nombre])
                redirect punto.verificacion
            }
            '*' { respond punto, [status: CREATED] }
        }
    }

    def edit(Punto punto) {

        respond punto, model:[materialesList:Material.list()]
    }

    @Transactional
    def upchange(Punto punto) {
        if (punto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (punto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond punto.errors, view:'edit'
            return
        }

		//Recuperando el archivo
        def file = request.getFile('tmp-foto_malo')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( directory + punto.foto_malo );
				if( file_real.exists() ){
					file_real.delete()
				}
				punto.foto_malo		= "PuntoMalo_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+punto.foto_malo))
			}
		}

		//Recuperando el archivo
        file = request.getFile('tmp-foto_bueno')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( directory + punto.foto_bueno );
				if( file_real.exists() ){
					file_real.delete()
				}
				punto.foto_bueno	= "PuntoBueno_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+punto.foto_bueno))
			}
		}

        if(punto.requiere_subsistema == false) {
            punto.subsistema = null
        }

        punto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'punto.label', default: 'Punto'), punto.nombre])
                redirect punto.verificacion
            }
            '*'{ respond punto, [status: OK] }
        }
    }

    @Transactional
    def delete(Punto punto) {

        if (verificacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        //Borrando archivo anexo 1
		def directory = grailsApplication.config.uploadFolder + "mes-icons/";
		def file_real = new File( directory + punto.foto_malo );
		if( file_real.exists() ){
			file_real.delete()
		}
		//Borrando archivo anexo 2
		file_real = new File( directory + punto.foto_bueno );
		if( file_real.exists() ){
			file_real.delete()
		}
		
        punto.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'punto.label', default: 'Punto'), punto.nombre])
                //redirect action:"index", method:"GET"
                redirect punto.verificacion
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Punto punto) {

        if (punto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		//Borrando archivo anexo 1
		def directory = grailsApplication.config.uploadFolder + "mes-icons/";
		def file_real = new File( directory + punto.foto_malo );
		if( file_real.exists() ){
			file_real.delete()
		}
		//Borrando archivo anexo 2
		file_real = new File( directory + punto.foto_bueno );
		if( file_real.exists() ){
			file_real.delete()
		}
		
		//Punto.executeUpdate("DELETE FROM puntos_materiales as pm WHERE pm.id_punto="+punto.id.toString())

        punto.delete flush:true

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'punto.label', default: 'Punto'), punto.nombre]);
		//redirect action:"index", method:"GET"
        redirect punto.verificacion

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'punto.label', default: 'Punto'), params.nombre]);
                //redirect action:"index", method:"GET"
                redirect punto.verificacion
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
