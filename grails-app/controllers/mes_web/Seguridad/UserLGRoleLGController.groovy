package mes_web

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserLGRoleLGController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		//Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("userLGRoleLG_search_offset")){
			params.offset = session.getAttribute("userLGRoleLG_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("userLGRoleLG_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("userLGRoleLG_search_rows")){
			params.max = session.getAttribute("userLGRoleLG_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("userLGRoleLG_search_word")) params.offset = 0;
        } else if (session.getAttribute("userLGRoleLG_search_word")){
			params.search_word = session.getAttribute("userLGRoleLG_search_word");
        }
        
        def userLGList = UserLG.createCriteria().list (params) {
            if ( params.search_word ) {
				ilike("username", "%${params.search_word}%")
            }
        }
        
        session.setAttribute("userLGRoleLG_search_rows", params.search_rows.toString());
        session.setAttribute("userLGRoleLG_search_word", params.search_word);
        session.setAttribute("userLGRoleLG_search_offset", params.offset.toString());
        
        respond UserLGRoleLG, model:[userLGRoleLGCount: UserLGRoleLG.totalCount]
    
    }

    def show(UserLGRoleLG userLGRoleLG) {
        respond userLGRoleLG
    }

    def create() {
        respond new UserLGRoleLG(params)
    }

    @Transactional
    def save(UserLGRoleLG userLGRoleLG) {
        if (userLGRoleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userLGRoleLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLGRoleLG.errors, view:'create'
            return
        }

        userLGRoleLG.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userLGRoleLG.label', default: 'UserLGRoleLG'), userLGRoleLG.id])
                redirect userLGRoleLG
            }
            '*' { respond userLGRoleLG, [status: CREATED] }
        }
    }

    def edit(UserLGRoleLG userLGRoleLG) {
        respond userLGRoleLG
    }

    @Transactional
    def update(UserLGRoleLG userLGRoleLG) {
        if (userLGRoleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userLGRoleLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLGRoleLG.errors, view:'edit'
            return
        }

        userLGRoleLG.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userLGRoleLG.label', default: 'UserLGRoleLG'), userLGRoleLG.id])
                redirect userLGRoleLG
            }
            '*'{ respond userLGRoleLG, [status: OK] }
        }
    }

    @Transactional
    def delete(UserLGRoleLG userLGRoleLG) {

        if (userLGRoleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userLGRoleLG.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLGRoleLG.label', default: 'UserLGRoleLG'), userLGRoleLG.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
	@Transactional
    def remove(UserLGRoleLG userLGRoleLG) {

        if (userLGRoleLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        userLGRoleLG.delete flush:true

		flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLGRoleLG.label', default: 'UserLGRoleLG'), userLGRoleLG.id])
		redirect action:"index", method:"GET"
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLGRoleLG.label', default: 'UserLGRoleLG'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
