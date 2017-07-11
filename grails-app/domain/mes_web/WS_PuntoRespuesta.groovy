package mes_web

import grails.rest.*

@Resource(uri='/WS_PuntoRespuesta', formats=['json', 'xml'])
class WS_PuntoRespuesta{

	String	id
    String	estado
    Integer semana
    Date	fregistro
    String	foto_evidencia
    String	observaciones
    Integer duracion

    static belongsTo = [punto: Punto]
	
    static constraints = {
		id				(nullable:false)
		estado			(nullable:false)
		semana			(nullable:false)
        fregistro		(nullable:true)
        foto_evidencia	(nullable:true)
        observaciones	(nullable:true)
        punto			(nullable:true)
        duracion		(nullable:false)
    }

    static mapping = {
		sort nombre: "asc"
		table 'PUNTOS_RESPUESTA'
		version false
		id column: "ID", generator : 'uuid'
        observaciones column:'observaciones', sqlType: 'text'
    }

    String toString() {
        return nombre 
    }
}
