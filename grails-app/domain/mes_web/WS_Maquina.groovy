package mes_web

import grails.rest.*

@Resource(uri='/WS_Maquina', formats=['json', 'xml'])
class WS_Maquina{
	
	String	id
    String	nombre
    String	descripcion
    String	archivo_Foto
    String	qrCode
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor

    static	belongsTo = [linea:Linea]
    static hasMany = [subsistemas: Subsistema]
	
    static constraints = {
		nombre			(nullable: false)
		linea			(nullable: false)
        descripcion		(nullable: true, widget: 'textarea')
        archivo_Foto	(nullable: true, widget:'icon')
        qrCode			(nullable:true, display:false, widget:'hiddenField')
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
        subsistemas		(nullable:true, display: false, widget:'hiddenField')
    }

    static mapping = {
		sort nombre: "asc"
		table 'MAQUINAS'
		version false
        id column: "ID", generator : 'uuid'
        descripcion column:'descripcion', sqlType: 'text'
        id_Autor column:'id_autor'
        subsistemas column:'id_maquina', cascade:'all-delete-orphan'
    }

    String toString() {
        return linea.planta.nombre+" ("+linea+") - "+nombre
    }
}
