package mes_web

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HorarioController {
	//Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		//Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("horario_search_offset")){
			params.offset = session.getAttribute("horario_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("horario_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("horario_search_rows")){
			params.max = session.getAttribute("horario_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("horario_search_word")) params.offset = 0;
        } else if (session.getAttribute("horario_search_word")){
			params.search_word = session.getAttribute("horario_search_word");
        }
        
        def horarioList = Horario.createCriteria().list (params) {
            if ( params.search_word ) {
				or {
					ilike("matricula", "%${params.search_word}%")
					ilike("fecha_Inicio", "%${params.search_word}%")
					ilike("fecha_Final", "%${params.search_word}%")
				}
            }
        }
        
        session.setAttribute("horario_search_rows", params.search_rows.toString());
        session.setAttribute("horario_search_word", params.search_word);
        session.setAttribute("horario_search_offset", params.offset.toString());
        
        respond horarioList, model:[horarioCount: horarioList.totalCount]
		
    }

    def show(Horario horario) {
        respond horario
    }

    def create() {
		Horario horario = new Horario(params)
		if (springSecurityService.principal)
			horario.id_Autor = UserLG.findById(springSecurityService.principal.id);
        respond horario
    }

    @Transactional
    def save(Horario horario) {
        if (horario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (horario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond horario.errors, view:'create'
            return
        }
        
        // Recupera el id del autor
		if (springSecurityService.principal)
			horario.id_Autor = UserLG.findById(springSecurityService.principal.id);
        
        horario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'horario.label', default: 'Horario'), horario.matricula])
                //redirect horario
                redirect action:"index", method:"GET"
            }
            '*' { respond horario, [status: CREATED] }
        }
    }

    def edit(Horario horario) {
        respond horario
    }

    @Transactional
    def update(Horario horario) {
        if (horario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (horario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond horario.errors, view:'edit'
            return
        }

        horario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'horario.label', default: 'Horario'), horario.matricula])
                //redirect horario
                redirect action:"index", method:"GET"
            }
            '*'{ respond horario, [status: OK] }
        }
    }

    @Transactional
    def delete(Horario horario) {

        if (horario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        horario.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'horario.label', default: 'Horario'), horario.matricula])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'horario.label', default: 'Horario'), params.matricula])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
