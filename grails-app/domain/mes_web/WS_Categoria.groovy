package mes_web

import grails.rest.*

@Resource(uri='/WS_Categoria',formats=['json', 'xml'])
class WS_Categoria{

    String  id
    String  nombre
    String  descripcion
    Date    dateCreated
    Date    lastUpdated
    UserLG  id_Autor
    
    WS_Categoria(nombre){
        this()
        this.nombre = nombre
    }

    static hasMany = [materiales: Material]

    static constraints = {
        nombre          (nullable: false)
        descripcion     (nullable: true, widget: 'textarea')
        dateCreated     (widget:'display')
        lastUpdated     (widget:'display')
        id_Autor        (nullable:true, widget:'display')
        materiales      (nullable:true, display: false, widget:'hiddenField')
    }

    static mapping = {
		sort nombre: "asc"
        table 'CATEGORIAS'
        version false
        id column: "ID", generator : 'uuid'
        descripcion column:'descripcion', sqlType: 'text'
        id_Autor column:'id_autor'
        materiales column:'id_material', cascade:'all-delete-orphan'
    }

    String toString() {
        return nombre
    }
}
