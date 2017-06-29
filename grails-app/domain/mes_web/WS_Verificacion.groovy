package mes_web

import grails.rest.*

@Resource(formats=['json', 'xml'])
class WS_Verificacion {

    String  id
    String  nombre
    String	claseVerificacion
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor

    static  belongsTo = [maquina:Maquina, mantenimiento:TipoMantenimiento, tipo:TipoVerificacion]

	static hasMany = [puntos: Punto]

    static constraints = {
		id				(nullable:true)
        maquina         (nullable: false)
        mantenimiento   (nullable: false)
        nombre          (nullable: false)
        tipo            (nullable: false)
        claseVerificacion (nullable: true)
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
        puntos			(nullable:true, display: false, widget:'hiddenField')
    }

    static mapping = {
        table 'LISTA_VERIFICACION'
        version false
        id column: "ID", generator : 'uuid'
        id_Autor column:'id_autor'
        puntos column:'id_verficacion', cascade:'all-delete-orphan'
    }

    String toString() {
        return nombre
    }
}
