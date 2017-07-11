package mes_web

import grails.rest.*

@Resource(uri='/WS_TipoVerificacion', formats=['json', 'xml'])
class WS_TipoVerificacion {

    String  id
    String  nombre
    String  descripcion
    Date    dateCreated
    Date    lastUpdated
    UserLG  id_Autor

    WS_TipoVerificacion(nombre){
        this()
        this.nombre = nombre
    }

    static constraints = {
        id              (nullable:true)
        nombre          (nullable: false)
        descripcion     (nullable: true, widget: 'textarea')
        dateCreated     (widget:'display')
        lastUpdated     (widget:'display')
        id_Autor        (nullable:true, widget:'display')
    }

    static mapping = {
		sort nombre: "asc"
        table 'TIPO_VERIFICACION'
        version false
        id column: "ID", generator : 'uuid'
        descripcion column:'descripcion', sqlType: 'text'
        id_Autor column:'id_autor'
    }

    String toString() {
        return nombre
    }
}
