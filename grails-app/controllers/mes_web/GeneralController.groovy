package mes_web

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus
import grails.transaction.*

class GeneralController {

    def mes() {

    }

    def tiposMantenimiento() {

    }

	def prevencionAccidentes() {

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'planta.label', default: 'Planta'), params.nombre])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
