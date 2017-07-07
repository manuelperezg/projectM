package mes_web

import grails.rest.*

@Resource(uri='/WS_Linea',formats=['json', 'xml'])
class WS_Linea{

	String	id
    String	nombre
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor 

    static belongsTo = [planta: Planta]
    static hasMany = [maquinas: Maquina]
	
    static constraints = {
		id				(nullable:true)
		nombre			(nullable:false)
		planta			(nullable:false)
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
        maquinas		(nullable:true, display: false, widget:'hiddenField')
    }

    static mapping = {
		sort nombre: "asc"
		table 'LINEAS'
		version false
		id column: "ID", generator : 'uuid'
        id_Autor column:'id_autor'
        maquinas column:'id_linea', cascade:'all-delete-orphan'
    }

    String toString() {
        return nombre 
    }
}
