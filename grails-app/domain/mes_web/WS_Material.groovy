package mes_web

import grails.rest.*

@Resource(uri='/WS_Material',formats=['json', 'xml'])
class WS_Material {

    String  id
    String  nombre
    String  archivo_Foto
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor

    static  belongsTo = [categoria:Categoria]

    static constraints = {
        nombre          (nullable: false)
        archivo_Foto    (nullable: true, widget:'icon')
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
    }

    static mapping = {
		sort nombre: "asc"
        table 'MATERIALES'
        version false
        id column: "ID", generator : 'uuid'
        id_Autor column:'id_autor'
    }

    String toString() {
        return nombre
    }
}
