package mes_web

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.SpringSecurityUtils

import grails.converters.JSON
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

//@Transactional(readOnly = true)
@Secured('permitAll')
class LogoutController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	/** Dependency injection for the springSecurityService. */
	def springSecurityService
	
    def index = {
		def principal = springSecurityService.principal;
        String username = principal.username;
        if (username.indexOf("anonymous") < 0){
			int id_User = principal.id;
			//println username;
			//println user_id;
			//Eliminando registro de sesion
			Sesion.findAll("FROM Sesion as s WHERE s.id_User='"+id_User.toString()+"'").each {
				it.delete(flush:true, failOnError:true);
			}
        }
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
    }

    
}
