package org.weykhans

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.web.context.request.RequestContextHolder


public class LoginEventListener implements ApplicationListener <AuthenticationSuccessEvent> {

    @Override
    void onApplicationEvent(final AuthenticationSuccessEvent event) {
		def session = RequestContextHolder.currentRequestAttributes().getSession();
        /*
        print 'I am logged in ...';
		def user = event.getAuthentication().getPrincipal();
        String username = user.username;
        Alumnos alumno = Alumnos.find("FROM Alumnos as a WHERE a.matricula='"+username+"'");
        if (alumno.size()){
			username = alumno.nombre;
        }
		session.setAttribute("username", username) 
		*/
		session.setAttribute("redirect", "/login/checkTime")
    }

}
