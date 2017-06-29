<%=packageName ? "package ${packageName}" : ''%>

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional(readOnly = true)
class ${className}Controller {

    //Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("${propertyName}_search_offset")){
			params.offset = session.getAttribute("${propertyName}_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("${propertyName}_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("${propertyName}_search_rows")){
			params.max = session.getAttribute("${propertyName}_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("${propertyName}_search_word")) params.offset = 0;
        } else if (session.getAttribute("${propertyName}_search_word")){
			params.search_word = session.getAttribute("${propertyName}_search_word");
        }
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        def itemsList = ${className}.createCriteria().list (params) {
			/*
            if ( params.search_word ) {
				or {
					ilike("nombre_1", "%\${params.search_word}%")
					ilike("nombre_2", "%\${params.search_word}%")
				}
            }
            */
            if ( params.search_word ) {
				ilike("nombre", "%\${params.search_word}%")
            }
        }
        
        session.setAttribute("${propertyName}_search_rows", params.search_rows);
        session.setAttribute("${propertyName}_search_word", params.search_word);
        session.setAttribute("${propertyName}_search_offset", params.offset);
        
        respond itemsList, model:[${propertyName}Count: itemsList.totalCount]
    }

    def show(${className} ${propertyName}) {
        respond ${propertyName}
    }

    def create() {
        respond new ${className}(params)
    }

    @Transactional
    def save(${className} ${propertyName}) {
        if (${propertyName} == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (${propertyName}.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ${propertyName}.errors, view:'create'
            return
        }
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////                                  MODIFICAR SI CONSIDERA                                        ////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Recupera el id del autor y lo asigna
		/*
		if (springSecurityService.principal)
			${propertyName}.id_Autor = UserLG.findById(springSecurityService.principal.id);
		*/
		
        ${propertyName}.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: '${propertyName}.label', default: '${className}'), ${propertyName}.id])
                redirect ${propertyName}
            }
            '*' { respond ${propertyName}, [status: CREATED] }
        }
    }

    def edit(${className} ${propertyName}) {
        respond ${propertyName}
    }

    @Transactional
    def update(${className} ${propertyName}) {
        if (${propertyName} == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (${propertyName}.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ${propertyName}.errors, view:'edit'
            return
        }

        ${propertyName}.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: '${propertyName}.label', default: '${className}'), ${propertyName}.id])
                redirect ${propertyName}
            }
            '*'{ respond ${propertyName}, [status: OK] }
        }
    }

    @Transactional
    def delete(${className} ${propertyName}) {
		
        if (${propertyName} == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        ${propertyName}.delete flush:true
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: '${propertyName}.label', default: '${className}'), ${propertyName}.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(${className} ${propertyName}) {
		
        if (${propertyName} == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        ${propertyName}.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: '${propertyName}.label', default: '${className}'), ${propertyName}.id])
        redirect action:"index", method:"GET"
        
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: '${propertyName}.label', default: '${className}'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
