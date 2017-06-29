package mes_web

import grails.rest.*

@Resource(formats=['json', 'xml'])
class WS_Pieza{

    String  id
    String  nombre
    String  descripcion
    Integer numero
    String  archivo_Foto
    String  clasificacion
    Date 	mantenimientoPlaneado
    boolean	seRepite = Boolean.FALSE
    String  frecuencia
    Integer numeroEventos
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor

    static  belongsTo = [subsistema:Subsistema]
	
    static constraints = {
		id				(nullable:true)
        subsistema	    (nullable: false)
        punto           (nullable: true)
        nombre          (nullable: false)
        archivo_Foto    (nullable: true, widget:'icon')
        actividad       (nullable: false, widget: 'textarea')
        responsable     (nullable: false, inList: ["Operador", "Supervisor"])
        frecuencia      (nullable: false, inList: ["Una vez por turno", "Una vez por dia", "Una vez por semana", "Una vez por mes"])
        estado          (nullable: false, inList: ["Parada", "Trabajando"])
        tiempo          (nullable: false)
        descripcion     (nullable: false, widget: 'textarea')
        dispositivos    (nullable: false, maxSize: 255, inList: ["Equipo basico (Lentes y guantes)"])
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
    }

    static mapping = {
        table 'PIEZAS'
        version false
        id column: "ID", generator : 'uuid'
        descripcion column:'descripcion', sqlType: 'text'
        actividad column:'actividad', sqlType: 'text'
        id_Autor column:'id_autor'
    }

    String toString() {
        return nombre
    }
}
