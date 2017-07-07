package mes_web

import grails.rest.*

@Resource(uri='/WS_Subsistema', formats=['json', 'xml'])
class WS_Subsistema {
	
	String	id
    String	nombre
    String	descripcion
    String	archivo_Foto
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor 

    static	belongsTo = [maquina:Maquina]
    //static hasMany = [piezas: Pieza]
	
    static constraints = {
		nombre			(nullable: false)
		maquina			(nullable: false)
        descripcion		(nullable: true, widget: 'textarea')
        archivo_Foto	(nullable: true, widget:'icon')
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
        //piezas			(nullable:true, display: false, widget:'hiddenField')
    }

    static mapping = {
		sort nombre: "asc"
		table 'SUBSISTEMAS'
		version false
        id column: "ID", generator : 'uuid'
        descripcion column:'descripcion', sqlType: 'text'
        id_Autor column:'id_autor'
        //piezas column:'id_pieza', cascade:'all-delete-orphan'
    }

    String toString() {
        return nombre
    }
}
