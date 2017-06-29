package mes_web

import grails.rest.*

@Resource(uri='/WS_Planta', formats=['json', 'xml'])

class WS_Planta{
	
	String	id
    String	nombre
    String	direccion
    String	archivo_Foto
    String	archivo_Esquema
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor
	
    static hasMany = [lineas: Linea]
	
    static constraints = {
		id				(nullable:true)
        nombre			(nullable: false)
        direccion		(nullable: true, widget:'textarea')
        archivo_Foto	(nullable: true, widget:'icon')
        archivo_Esquema	(nullable: true, widget:'icon')
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
        lineas			(nullable:true, display: false, widget:'hiddenField')
    }

    static mapping = {
		sort nombre: "asc"
		table 'PLANTAS'
		version false
        id column: "ID", generator : 'uuid'
        id_Autor column:'id_autor'
        lineas column:'id_planta', cascade:'all-delete-orphan'
    }

    String toString() {
        return nombre
    }
}
