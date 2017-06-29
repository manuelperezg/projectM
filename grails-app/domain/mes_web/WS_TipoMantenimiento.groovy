package mes_web

import grails.rest.*

@Resource(formats=['json', 'xml'])
class WS_TipoMantenimiento{

	String	id
    String	nombre
    String	descripcion
    String	archivo_Foto
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor

    WS_TipoMantenimiento(nombre, descripcion){
        this()
        this.nombre = nombre
        this.descripcion = descripcion
    }

    static constraints = {
		id				(nullable:true)
        nombre			(nullable: false)
        descripcion		(nullable: true, widget: 'textarea')
        archivo_Foto	(nullable: true, widget:'icon')
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
    }

    static mapping = {
		sort nombre: "asc"
		table 'TIPO_MANTENIMIENTO'
		version false
        id column: "ID", generator : 'uuid'
        descripcion column:'descripcion', sqlType: 'text'
        id_Autor column:'id_autor'
    }

    String toString() {
        return nombre
    }
}
