package mes_web

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.access.annotation.Secured
import org.springframework.security.authentication.AuthenticationTrustResolver

//import org.grails.web.servlet.mvc.GrailsWebRequest

import org.springframework.web.context.request.RequestContextHolder
import javax.servlet.http.HttpServletResponse

@Secured('permitAll')
class LoginController {
	
	/** Dependency injection for the authenticationTrustResolver. */
	AuthenticationTrustResolver authenticationTrustResolver

	/** Dependency injection for the springSecurityService. */
	def springSecurityService
	
	/** Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise. */
	def index() {
		if (springSecurityService.isLoggedIn()) {
			redirect uri: conf.successHandler.defaultTargetUrl
		}
		else {
			redirect action: 'auth', params: params
		}
	}
	
	def noInscrito(String username){
		flash.message = username + " NO se encuentra inscrito actualmente.";
		redirect action: 'auth', params: params
	}
	
	def noHora(String horario){
		if (horario != "") {
			flash.message = "Su horario de acceso es " + horario + ".";
		} else {
			flash.message = "No existe un horario definido para su acceso a\u00fan.";
		}
		redirect action: 'auth', params: params
	}
	
	def noSesion(String username, String fecha_Ingreso){
		flash.message = username + " tiene una sesi\u00f3n abierta con tiempo de ingreso " + fecha_Ingreso + ".";
		redirect action: 'auth', params: params
	}
	
	def checkTime() {
		String sessionId = RequestContextHolder.getRequestAttributes()?.getSessionId()
		//print "Session:"+sessionId
		
		//Eliminando el ciclo de redireccionamiento infinito
		session.setAttribute("redirect", "");
		
		def isInscrito = true;
		def isHora = false;
		String horario = "";
		def isSesion = false;
		String fecha_Ingreso = "";
		
		def principal = springSecurityService.principal;
        String username = principal.username;
        String name = username;
        int id_User = principal.id;
        
        //println username;
        //println id_User;
		
		name = toTitleCase(name);
		session.setAttribute("username_main", name);
		
		UserLG usuario = UserLG.findById(id_User)
        if (usuario == null) {
            session.setAttribute("username_nombre", "¡Desconocido!");
			session.setAttribute("username_archivo_Foto", "");
			session.setAttribute("username_rolActivo", "");
        }else{
			session.setAttribute("username_nombre", usuario.nombre);
			session.setAttribute("username_archivo_Foto", usuario.archivo_Foto);
			session.setAttribute("username_rolActivo", usuario.getRolName());
        }
		
		//UserLG.findAll("FROM UserLG as u WHERE u.username='"+principal.username+"'").each {
			//session.setAttribute("username_nombre", it.nombre.toString());
			//session.setAttribute("username_archivo_Foto", it.archivo_Foto.toString());
		//}
		
		Date now = new Date();
		
		//Validando que el usuario este inscrito
		if (!isInscrito){
			session.invalidate();
			/*
			GrailsWebRequest request = RequestContextHolder.currentRequestAttributes()
			GrailsWebRequest.lookup(request).session = null
			*/
			redirect action: 'noInscrito', params: [username: username]
			//redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl + "?spring-security-redirect=login/auth";
		}else{
			//Validando que el usuario este en el tiempo indicado
			Horario.list(sort: 'matricula', order: 'desc').each {
				//Revisar los horarios para ver cual coincide
				if (username.indexOf(it.matricula) >= 0) {
					horario = it.fecha_Inicio + " a " + it.fecha_Final;
					if (now >= Date.parse('dd/MM/yyyy HH:mm', it.fecha_Inicio) && now <= Date.parse('dd/MM/yyyy HH:mm', it.fecha_Final)){
						isHora = true;
					}
					//print now;
					//print horario;
				}
			}
			//Elimina la validación de horario para los usuarios no estudiantes
			if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString() != "[ROLE_STUDENT]") isHora = true;
			
			if (!isHora) {
				session.invalidate();
				redirect action: 'noHora', params: [horario: horario]
			} else {
				//Validando la sesion
				if (Sesion.count() > 0){
					Sesion.findAll("FROM Sesion as s WHERE s.id_User='"+id_User.toString()+"'").each {
						//Revisando el tiempo de la sesion, si pasa de 15 minutos habilita nueva sesion
						fecha_Ingreso = it.fecha_Ingreso;
						Date ingreso = Date.parse('dd/MM/yyyy HH:mm', fecha_Ingreso);
						long diferencia = ((System.currentTimeMillis()-ingreso.time)/1000)/60;
						if (diferencia >= 15){
							it.delete(flush:true, failOnError:true);
						}else{
							isSesion = true;
						}
					}
				}
				
				if (isSesion){
					session.invalidate();
					redirect action: 'noSesion', params: [username: username, fecha_Ingreso: fecha_Ingreso]
				}else{
					//Registrando sesion
					Sesion sesion = new Sesion()
					
					/*
					int id = 0;
					if (Sesion.count() > 0){
						if (Sesion.createCriteria().get {projections {max "id"}} as Integer != null){
						   id = Sesion.createCriteria().get {projections {max "id"}} as int
						}
					}
					id++
					sesion.id = id
					*/
					
					sesion.id_User = id_User
					sesion.sesion_No = sessionId
					sesion.fecha_Ingreso = now.format( 'dd/MM/yyyy HH:mm' )
					sesion.save flush:true

					if (springSecurityService.isLoggedIn()) {
						redirect uri: conf.successHandler.defaultTargetUrl
					} else {
						redirect action: 'auth', params: params
					}
				}
			}
		}
	}
	
	/** Show the login page. */
	def auth() {
		def conf = getConf()

		if (springSecurityService.isLoggedIn()) {
			redirect uri: conf.successHandler.defaultTargetUrl
			return
		}

		String postUrl = request.contextPath + conf.apf.filterProcessesUrl
		render view: 'auth', model: [postUrl: postUrl,
		                             rememberMeParameter: conf.rememberMe.parameter,
		                             usernameParameter: conf.apf.usernameParameter,
		                             passwordParameter: conf.apf.passwordParameter,
		                             gspLayout: conf.gsp.layoutAuth]
	}

	/** The redirect action for Ajax requests. */
	def authAjax() {
		response.setHeader 'Location', conf.auth.ajaxLoginFormUrl
		render(status: HttpServletResponse.SC_UNAUTHORIZED, text: 'Unauthorized')
	}

	/** Show denied page. */
	def denied() {
		if (springSecurityService.isLoggedIn() && authenticationTrustResolver.isRememberMe(authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY (or the equivalent expression)
			redirect action: 'full', params: params
			return
		}

		[gspLayout: conf.gsp.layoutDenied]
	}

	/** Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page. */
	def full() {
		def conf = getConf()
		render view: 'auth', params: params,
		       model: [hasCookie: authenticationTrustResolver.isRememberMe(authentication),
		               postUrl: request.contextPath + conf.apf.filterProcessesUrl,
		               rememberMeParameter: conf.rememberMe.parameter,
		               usernameParameter: conf.apf.usernameParameter,
		               passwordParameter: conf.apf.passwordParameter,
		               gspLayout: conf.gsp.layoutAuth]
	}
	
	/** Callback after a failed login. Redirects to the auth page with a warning message. */
	def authfail() {
		String msg = ''
		def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
		if (exception) {
			if (exception instanceof AccountExpiredException) {
				msg = message(code: 'springSecurity.errors.login.expired')
			}
			else if (exception instanceof CredentialsExpiredException) {
				msg = message(code: 'springSecurity.errors.login.passwordExpired')
			}
			else if (exception instanceof DisabledException) {
				msg = message(code: 'springSecurity.errors.login.disabled')
			}
			else if (exception instanceof LockedException) {
				msg = message(code: 'springSecurity.errors.login.locked')
			}
			else {
				msg = message(code: 'springSecurity.errors.login.fail')
			}
		}

		if (springSecurityService.isAjax(request)) {
			render([error: msg] as JSON)
		}
		else {
			flash.message = msg
			redirect action: 'auth', params: params
		}
	}

	/** The Ajax success redirect url. */
	def ajaxSuccess() {
		render([success: true, username: authentication.name] as JSON)
	}

	/** The Ajax denied redirect url. */
	def ajaxDenied() {
		render([error: 'access denied'] as JSON)
	}

	protected Authentication getAuthentication() {
		SecurityContextHolder.context?.authentication
	}

	protected ConfigObject getConf() {
		SpringSecurityUtils.securityConfig
	}
	
	private String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;
        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true; //true = Hola Mundo Nuevo
            } else if (nextTitleCase) {
                c = Character.toUpperCase(c);
                nextTitleCase = false;
            }else{
                c = Character.toLowerCase(c);
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }
}
