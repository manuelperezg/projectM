package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class MaterialController {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("material_search_offset")){
			params.offset = session.getAttribute("material_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("material_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("material_search_rows")){
			params.max = session.getAttribute("material_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("material_search_word")) params.offset = 0;
        } else if (session.getAttribute("material_search_word")){
			params.search_word = session.getAttribute("material_search_word");
        }
        //Recuperación de filtro
		if (params.filter_categoria) {
			if (params.filter_categoria != session.getAttribute("search_filter_categoria")) params.offset = 0;
        } else if (session.getAttribute("search_filter_categoria")){
			params.filter_categoria = session.getAttribute("search_filter_categoria");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		
        def itemsList = Material.createCriteria().list (params) {
			/*
            if ( params.search_word ) {
				or {
					ilike("nombre_1", "%${params.search_word}%")
					ilike("nombre_2", "%${params.search_word}%")
				}
            }
            */
             if (params.filter_categoria && params.filter_categoria != "ALL") {
                eq("categoria.id", params.filter_categoria)
            }
            if ( params.search_word ) {
				ilike("nombre", "%${params.search_word}%")
            }
            if ( !params.sort ){
				order("nombre","asc");
            }
        }
        
        session.setAttribute("material_search_rows", params.search_rows);
        session.setAttribute("material_search_word", params.search_word);
        session.setAttribute("material_search_offset", params.offset);
        session.setAttribute("search_filter_categoria", params.filter_categoria);
        
        render(view:'index', model: [materialList:itemsList, materialCount: itemsList.totalCount,categorias:Categoria.list(sort: "nombre", order: "asc")])

        //respond itemsList, model:[materialCount: itemsList.totalCount]
    }

    def show(Material material) {
        respond material
    }

    def create() {
		Material material = new Material(params)
		if (springSecurityService.principal)
			material.id_Autor = UserLG.findById(springSecurityService.principal.id);
		if (session.getAttribute("search_filter_categoria") && session.getAttribute("search_filter_categoria") != "ALL"){
			material.categoria = Categoria.findById(session.getAttribute("search_filter_categoria"));
		}
		respond material
    }

    @Transactional
    def save(Material material) {
        if (material == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (material.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond material.errors, view:'create'
            return
        }
		
		// Recupera el id del autor
		if (springSecurityService.principal)
			material.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
		//Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')

        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-icons/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				material.archivo_Foto = "Material_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+material.archivo_Foto))
			}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		if (springSecurityService.principal)
			material.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
		
        material.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'material.label', default: 'Material'), material.nombre])
                //redirect material
                redirect action:"index", method:"GET"
            }
            '*' { respond material, [status: CREATED] }
        }
    }

    def edit(Material material) {
        respond material
    }

    @Transactional
    def upchange(Material material) {
        if (material == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (material.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond material.errors, view:'edit'
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
				material.archivo_Foto = "Material_" + minutesName.toString() + extension ;

				file.transferTo(new File(directory+material.archivo_Foto))
			}
		}
		
        material.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'material.label', default: 'Material'), material.nombre])
                //redirect material
                redirect action:"index", method:"GET"
            }
            '*'{ respond material, [status: OK] }
        }
    }

    @Transactional
    def delete(Material material) {
		
        if (material == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Detectar los puntos que tienen materiales
		if (Punto.findAll("from Punto as items inner join items.materiales material where material.id = '"+material.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'material.label', default: 'Material'), material.nombre])
		}else{
			material.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'material.label', default: 'Material'), material.nombre])
		}
		
        request.withFormat {
            form multipartForm {
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(Material material) {
		
        if (material == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Detectar los puntos que tienen materiales
		if (Punto.findAll("from Punto as items inner join items.materiales material where material.id = '"+material.id+"'").toList().size() > 0){
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'material.label', default: 'Material'), material.nombre])
		}else{
			material.delete flush:true
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'material.label', default: 'Material'), material.nombre])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'material.label', default: 'Material'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
