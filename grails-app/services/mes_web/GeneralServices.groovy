package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.web.context.request.RequestContextHolder


public class GeneralServices{

    public void updateSession(int userid){
		Date now = new Date();
		
		Sesion sesion = Sesion.find("FROM Sesion as s WHERE s.user_id="+userid.toString());
		if (sesion){
			sesion.fingreso = now.format( 'dd/MM/yyyy HH:mm' )
			sesion.save flush:true
		}
    }
}
