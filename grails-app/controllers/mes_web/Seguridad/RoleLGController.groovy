package mes_web

import static org.springframework.http.HttpStatus.*
import grails.transaction.*
import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.transaction.TransactionStatus

@Transactional(readOnly = true)
class RoleLGController {
    //Identificación de la sesion del usuario
    def springSecurityService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //Recuperación de la pagina en que se encuentra
        if (params.offset){

        }else if (session.getAttribute("roleLG_search_offset")){
			params.offset = session.getAttribute("roleLG_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("roleLG_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("roleLG_search_rows")){
			params.max = session.getAttribute("roleLG_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("roleLG_search_word")) params.offset = 0;
        } else if (session.getAttribute("roleLG_search_word")){
			params.search_word = session.getAttribute("roleLG_search_word");
        }

        def itemsList = RoleLG.createCriteria().list (params) {
            if ( params.search_word ) {
				or {
					ilike("authority", "%${params.search_word}%")
					ilike("nombre", "%${params.search_word}%")
				}
            }
            /*
            if ( params.search_word ) {
				ilike("authority", "%${params.search_word}%")
            }
            */
            if ( !params.sort ){
				order("nombre","asc");
            }
        }

        session.setAttribute("roleLG_search_rows", params.search_rows);
        session.setAttribute("roleLG_search_word", params.search_word);
        session.setAttribute("roleLG_search_offset", params.offset);

        respond itemsList, model:[roleLGCount: itemsList.totalCount]
    }

    def show(RoleLG roleLG) {
        respond roleLG
    }

    def lineas(RoleLG roleLG) {
        respond roleLG,  model:[lineas: Linea.list()]
    }

    def create() {
		RoleLG role = new RoleLG(params)
		role.authority = "ROLE_"
		if (springSecurityService.principal)
			role.id_Autor = UserLG.findById(springSecurityService.principal.id);
        respond role
    }

    @Transactional
    def save(RoleLG roleLG) {
        if (roleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roleLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roleLG.errors, view:'create'
            return
        }

		// Recupera el id del autor
		if (springSecurityService.principal)
			roleLG.id_Autor = UserLG.findById(springSecurityService.principal.id);

        roleLG.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'roleLG.label', default: 'RoleLG'), roleLG.nombre])
                //redirect roleLG
                redirect action:"index", method:"GET"
            }
            '*' { respond roleLG, [status: CREATED] }
        }
    }

    @Transactional
    def save_lineas(RoleLG roleLG) {
        if (roleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roleLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roleLG.errors, view:'lineas'
            return
        }

        roleLG.lineas.clear()
        roleLG.save flush:true

        if (params.lineas instanceof String) {
            Linea linea = Linea.get(params.lineas)
            roleLG.addToLineas(linea)
        }
        else {
            params.lineas.each {
                Linea linea = Linea.get(it)
                roleLG.addToLineas(linea)
            }
        }

        roleLG.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'roleLG.lineas.created.message', args: [message(code: 'roleLG.label', default: 'RoleLG'), roleLG.nombre])
                //redirect roleLG
                redirect action:"index", method:"GET"
            }
            '*' { respond roleLG, [status: CREATED] }
        }
    }

    def edit(RoleLG roleLG) {
        respond roleLG
    }

    @Transactional
    def update(RoleLG roleLG) {
        if (roleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roleLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roleLG.errors, view:'edit'
            return
        }

        roleLG.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'roleLG.label', default: 'RoleLG'), roleLG.nombre])
                //redirect roleLG
                redirect action:"index", method:"GET"
            }
            '*'{ respond roleLG, [status: OK] }
        }
    }

    @Transactional
    def delete(RoleLG roleLG) {

        if (roleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		UserLGRoleLG.executeUpdate("DELETE FROM UserLGRoleLG as ur WHERE ur.roleLG.id="+roleLG.id.toString());
        roleLG.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'roleLG.label', default: 'RoleLG'), roleLG.nombre])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

	@Transactional
    def remove(RoleLG roleLG) {

        if (roleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		UserLGRoleLG.executeUpdate("DELETE FROM UserLGRoleLG as ur WHERE ur.roleLG.id="+roleLG.id.toString());
        roleLG.delete flush:true

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'roleLG.label', default: 'RoleLG'), roleLG.nombre])
		redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'roleLG.label', default: 'RoleLG'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
