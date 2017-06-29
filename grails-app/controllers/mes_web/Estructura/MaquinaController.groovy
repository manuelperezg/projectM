package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class MaquinaController {
    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("maquina_search_offset")){
			params.offset = session.getAttribute("maquina_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("maquina_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("maquina_search_rows")){
			params.max = session.getAttribute("maquina_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("maquina_search_word")) params.offset = 0;
        } else if (session.getAttribute("maquina_search_word")){
			params.search_word = session.getAttribute("maquina_search_word");
        }
		//Recuperación de filtro
		if (params.filter_planta) {
			if (params.filter_planta != session.getAttribute("search_filter_planta")) params.offset = 0;
        } else if (session.getAttribute("search_filter_planta")){
			params.filter_planta = session.getAttribute("search_filter_planta");
        }
		if (params.filter_linea) {
			if (params.filter_linea != session.getAttribute("search_filter_linea")) params.offset = 0;
        } else if (session.getAttribute("search_filter_linea")){
			params.filter_linea = session.getAttribute("search_filter_linea");
        }
        def itemsList = Maquina.createCriteria().list (params) {
			/*
            if ( params.search_word ) {
				or {
					ilike("nombre_1", "%${params.search_word}%")
					ilike("nombre_2", "%${params.search_word}%")
				}
            }
            */

            if (params.filter_planta && params.filter_planta != "ALL") {
                linea {
                    eq("planta.id", params.filter_planta)
                }
            }

            if (params.filter_linea && params.filter_linea != "ALL") {
                eq("linea.id", params.filter_linea)
            }

            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
        }
		
		// buscar lineas que pertenecen a la planta
        def lineas = Linea.createCriteria().list (sort: "nombre", order: "asc") {
            if (params.filter_planta && params.filter_planta != "ALL") {
                eq("planta.id", params.filter_planta)
            }
        }
		
        session.setAttribute("maquina_search_rows", params.search_rows);
        session.setAttribute("maquina_search_word", params.search_word);
        session.setAttribute("maquina_search_offset", params.offset);
		session.setAttribute("search_filter_planta", params.filter_planta);
		session.setAttribute("search_filter_linea", params.filter_linea);
		
		render(view:'index', model: [maquinaList:itemsList, maquinaCount: itemsList.totalCount, lineas: lineas, plantas:Planta.list(sort: "nombre", order: "asc")])
        
        //respond itemsList, model:[maquinaCount: itemsList.totalCount, lineas: lineas, plantas: Planta.list()]
    }

    def show(Maquina maquina) {
		if (params.offset){
				session.setAttribute("subsistema_search_offset", offset);
		}
		if (!session.getAttribute("subsistema_search_offset")){
			session.setAttribute("subsistema_search_offset", 0);
		}
		
		def orderField = "nombre"
		if (params.sort) {
			orderField = params.sort
		}
		def orderMethod = "asc"
		if (params.order) {
			orderMethod = params.order
		}
        
        def itemsList = Subsistema.createCriteria().list (max: 10, offset: session.getAttribute("subsistema_search_offset")) {
            eq("maquina.id", maquina.id)
            order(orderField,orderMethod)
        }
        
        respond maquina, model:[subsistemaCount: itemsList.size(), subsistemas: itemsList]
        //respond maquina
    }

    def create() {
		Maquina maquina = new Maquina(params)
		if (params.linea) {
			maquina.linea = Linea.findById(params.linea);
		}
        maquina.qrCode = UUID.randomUUID().toString()
		if (springSecurityService.principal)
			maquina.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
		/*
		if (session.getAttribute("search_filter_planta") && session.getAttribute("search_filter_planta") != "ALL"){
			maquina.planta = Planta.findById(session.getAttribute("search_filter_planta"));
		}
		if (session.getAttribute("search_filter_linea") && session.getAttribute("search_filter_linea") != "ALL"){
			maquina.linea = Linea.findById(session.getAttribute("search_filter_linea"));
		}
		*/
		
        respond maquina
    }

    @Transactional
    def save(Maquina maquina) {
        if (maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (maquina.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond maquina.errors, view:'create'
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
				maquina.archivo_Foto = "Maquina_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+maquina.archivo_Foto))
			}
		}

		// Recupera el id del autor
		if (springSecurityService.principal)
			maquina.id_Autor = UserLG.findById(springSecurityService.principal.id);

        maquina.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'maquina.label', default: 'Maquina'), maquina.nombre])
                redirect maquina
            }
            '*' { respond maquina, [status: CREATED] }
        }
    }

    def edit(Maquina maquina) {
        respond maquina
    }

    @Transactional
    def upchange(Maquina maquina) {
        if (maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (maquina.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond maquina.errors, view:'edit'
            return
        }

		//Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory = grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( directory + maquina.archivo_Foto );
				if( file_real.exists() ){
					file_real.delete()
				}
				maquina.archivo_Foto = "Maquina_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+maquina.archivo_Foto))
			}
		}

        maquina.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'maquina.label', default: 'Maquina'), maquina.nombre])
                redirect maquina
            }
            '*'{ respond maquina, [status: OK] }
        }
    }

    @Transactional
    def delete(Maquina maquina) {

        if (maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
		if (Subsistema.findAll("from Subsistema as items where items.maquina.id = '"+maquina.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'maquina.label', default: 'Maquina'), maquina.nombre])
		}else{
			//Borrando archivo anterior
			def directory = grailsApplication.config.uploadFolder + "mes-icons/";
			def file_real = new File( directory + maquina.archivo_Foto );
			if( file_real.exists() ){
				file_real.delete()
			}
			
			maquina.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'maquina.label', default: 'Maquina'), maquina.nombre])
		}
		
        request.withFormat {
            form multipartForm {
                redirect maquina.linea
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Maquina maquina) {

        if (maquina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		if (Subsistema.findAll("from Subsistema as items where items.maquina.id = '"+maquina.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'maquina.label', default: 'Maquina'), maquina.nombre])
		}else{
			//Borrando archivo anterior
			def directory = grailsApplication.config.uploadFolder + "mes-icons/";
			def file_real = new File( directory + maquina.archivo_Foto );
			if( file_real.exists() ){
				file_real.delete()
			}
			
			maquina.delete flush:true

			flash.message = message(code: 'default.deleted.message', args: [message(code: 'maquina.label', default: 'Maquina'), maquina.nombre])
		}
		
        redirect maquina.linea
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'maquina.label', default: 'Maquina'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
