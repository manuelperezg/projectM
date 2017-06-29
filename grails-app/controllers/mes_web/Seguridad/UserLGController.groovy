package mes_web

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserLGController {
	//Identificación de la sesion del usuario
    def springSecurityService;
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    def index(Integer max) {
		//Recuperación de la pagina en que se encuentra
        if (params.offset){
			
        }else if (session.getAttribute("userLG_search_offset")){
			params.offset = session.getAttribute("userLG_search_offset");
        } else {
			params.offset = 0;
        }
		//Recuperación de limite maximo de registros visibles
		if (params.search_rows) {
			if (params.search_rows != session.getAttribute("userLG_search_rows")) params.offset = 0;
			params.max = params.search_rows.toInteger();
        } else if (session.getAttribute("userLG_search_rows")){
			params.max = session.getAttribute("userLG_search_rows").toInteger();
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
			if (params.search_word != session.getAttribute("userLG_search_word")) params.offset = 0;
        } else if (session.getAttribute("userLG_search_word")){
			params.search_word = session.getAttribute("userLG_search_word");
        }
        
        def userLGList = UserLG.createCriteria().list (params) {
            if ( params.search_word ) {
				ilike("username", "%${params.search_word}%")
            }
            if ( !params.sort ){
				order("nombre","asc");
            }
        }
        
        session.setAttribute("userLG_search_rows", params.search_rows.toString());
        session.setAttribute("userLG_search_word", params.search_word);
        session.setAttribute("userLG_search_offset", params.offset.toString());
        
        respond userLGList, model:[userLGCount: userLGList.totalCount]
    }

    def show(UserLG userLG) {
		if (userLG == null) {
            notFound()
            return
        }
		userLG.decodePassword()
        respond userLG
    }
	
	def report(){
        String servidor = request.getRequestURL().toString().replace('http://', '').replace('https://', '').replace('HTTP://', '').replace('HTTPS://', '')
		def partes = servidor.tokenize('/:')
		servidor = partes[0]
		session.setAttribute("servidor", servidor)
    }
	
	def myuser() {
		String username = principal.username;
		UserLG userLG = UserLG.find("FROM UserLG as u WHERE u.username='"+username+"'");
 		if (userLG == null) {
            notFound()
            return
        }
		userLG.decodePassword()
        respond userLG
    }
    
	@Transactional
    def restart(UserLG userLG) {
		userLG.password = userLG.username
		userLG.save flush:true
		userLG.decodePassword()
		flash.message = message(code: 'default.restarted.message', args: [message(code: 'userLG.label', default: 'UserLG'), userLG.username])
        //redirect action:"show", id:userLG.id
        redirect action:"index", method:"GET"
    }
	
	@Transactional
    def active(UserLG userLG) {
		userLG.enabled = !userLG.enabled
		userLG.password = userLG.password_Plain
		userLG.save flush:true
		userLG.decodePassword()
		flash.message = message(code: 'default.reactive.message', args: [message(code: 'userLG.label', default: 'UserLG'), userLG.username])
        //redirect action:"show", id:userLG.id
        redirect action:"index", method:"GET"
    }

    def create() {
		UserLG user = new UserLG(params)
		if (springSecurityService.principal)
			user.id_Autor = UserLG.findById(springSecurityService.principal.id);
        respond user, model:[roles:RoleLG.list()]
    }

    @Transactional
    def save(UserLG userLG) {
        if (userLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLG.errors, view:'create', model:[roles:RoleLG.list()]
            return
        }
        
        //Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')
        
        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-users/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				userLG.archivo_Foto = "Usuario_" + minutesName.toString() + extension ;
				
				//println directory+userLG.archivo_Foto
				//println System.properties['base.dir']
				//println request.getSession().getServletContext().getRealPath("/")
				//println System.getProperty("user.home")
				//println servletContext.getRealPath("/")
				
				//println "${basedir}/grails-app/assets/images/mes-users"

				file.transferTo(new File(directory+userLG.archivo_Foto))
			}
		}
		
		// Recupera el id del autor
		if (springSecurityService.principal)
			userLG.id_Autor = UserLG.findById(springSecurityService.principal.id);
		
        userLG.save flush:true

        // Asignar rol
        UserLGRoleLG.create userLG, RoleLG.findByAuthority(params.role)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userLG.label', default: 'UserLG'), userLG.username])
                //redirect userLG
                redirect action:"index", method:"GET"
            }
            '*' { respond userLG, [status: CREATED] }
        }
    }
	
	@Transactional
    def updateMyUser(UserLG userLG) {
		
        if (userLG == null) {
            transactionStatus.setRollbackOnly()
            //notFound()
            respond userLG.errors, view:'myuser'
            return
        }
        if (userLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLG.errors, view:'myuser'
            return
        }
        
        //Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')
        
        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-users/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( directory + userLG.archivo_Foto )
				if( file_real.exists() ){
					file_real.delete()
				}
				userLG.archivo_Foto = "Usuario_" + minutesName.toString() + extension ;
								
				file.transferTo(new File(directory+userLG.archivo_Foto))
			}
		}
        
        userLG.save flush:true

        // Actualizar rol
        if( params.role != userLG.getRol() ) {
            UserLGRoleLG.removeAll(userLG, true)
            UserLGRoleLG.create userLG, RoleLG.findByAuthority(params.role)
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Usuario', default: 'Usuario'), userLG.username])
                redirect action: "myuser"
            }
            '*'{ respond userLG, [status: OK] }
        }
    }
	
    def edit(UserLG userLG) {
		userLG.decodePassword()
        respond userLG, model:[roles:RoleLG.list()]
    }

    @Transactional
    def upchange(UserLG userLG) {
		
        if (userLG == null) {
            transactionStatus.setRollbackOnly()
            //notFound()
            respond userLG.errors, view:'edit'
            return
        }
        if (userLG.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLG.errors, view:'edit'
            return
        }
        
        //Recuperando el archivo
        def file = request.getFile('tmp-archivo_Foto')
        
        if (!file.empty) {
			if (file.getInputStream() != null){
				def directory		= grailsApplication.config.uploadFolder + "mes-users/";
				def fileName		= file.originalFilename;
				String extension	= fileName.substring(fileName.lastIndexOf("."));
				//def minutesName	= Math.round(System.currentTimeMillis()/1000/60);
				def minutesName		= System.currentTimeMillis();
				//Borrando archivo anterior
				def file_real = new File( directory + userLG.archivo_Foto )
				if( file_real.exists() ){
					file_real.delete()
				}
				userLG.archivo_Foto = "Usuario_" + minutesName.toString() + extension ;
								
				file.transferTo(new File(directory+userLG.archivo_Foto))
			}
		}
        
        userLG.save flush:true

        // Actualizar rol
        if( params.role != userLG.getRol() ) {
            UserLGRoleLG.removeAll(userLG, true)
            UserLGRoleLG.create userLG, RoleLG.findByAuthority(params.role)
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Usuario', default: 'Usuario'), userLG.username])
                //redirect userLG
                redirect action:"index", method:"GET"
            }
            '*'{ respond userLG, [status: OK] }
        }
    }

    @Transactional
    def delete(UserLG userLG) {

        if (userLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        //Borrando archivo anexo
        def directory = grailsApplication.config.uploadFolder + "mes-users/";
		def file_real = new File( directory + userLG.archivo_Foto )
		if( file_real.exists() ){
			file_real.delete()
		}
		
		UserLGRoleLG.executeUpdate("DELETE FROM UserLGRoleLG as ur WHERE ur.userLG.id="+userLG.id.toString())
        userLG.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLG.label', default: 'UserLG'), userLG.username])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
	@Transactional
    def remove(UserLG userLG) {

        if (userLG == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		//Borrando archivo anexo
        def directory = grailsApplication.config.uploadFolder + "mes-users/";
		def file_real = new File( directory + userLG.archivo_Foto )
		if( file_real.exists() ){
			file_real.delete()
		}
		
		UserLGRoleLG.executeUpdate("DELETE FROM UserLGRoleLG as ur WHERE ur.userLG.id="+userLG.id.toString())
        userLG.delete flush:true
		
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLG.label', default: 'UserLG'), userLG.username])
        redirect action:"index", method:"GET"
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLG.label', default: 'UserLG'), params.username])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
